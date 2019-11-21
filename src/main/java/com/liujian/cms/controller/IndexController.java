package com.liujian.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liujian.cms.dao.ArticleRepository;
import com.liujian.cms.domain.Article;
import com.liujian.cms.domain.ArticleWithBLOBs;
import com.liujian.cms.domain.Category;
import com.liujian.cms.domain.Channel;
import com.liujian.cms.domain.Special;
import com.liujian.cms.service.ArticleService;
import com.liujian.cms.service.CategoryService;
import com.liujian.cms.service.ChannelService;
import com.liujian.cms.service.SpecialService;
import com.liujian.cms.util.ArticleEnum;
import com.liujian.cms.util.ESUtils;
import com.liujian.cms.util.PageUtil;
import com.liujian.cms.vo.ArticleVO;
import com.liujian.utils.DateUtil;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 
 * @ClassName: IndecController
 * @Description: cms首页
 * @author: liujian
 * @date: 2019年10月18日 上午10:18:37
 */

@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ArticleService articleService;
	@Resource
	private SpecialService specialService;
	@Resource
	private RedisTemplate redisTemplate;	
	//注入es仓库
	@Autowired
	private ArticleRepository articleRepository;
	//es模板
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	/**
	 * es搜索的
	 */
	@RequestMapping("search")
	public String search(String key,Model model,@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "2") Integer pageSize) {
		long start = System.currentTimeMillis();
		//高亮显示
		AggregatedPage<?> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, page, pageSize,new String[] {"title"}, key);
		List<?> list = selectObjects.getContent();
		System.out.println(key);
		long end = System.currentTimeMillis();
		System.err.println("搜索耗时"+(end-start));
		model.addAttribute("hotArticles", list);
		model.addAttribute("key", key);
		String pages = PageUtil.page(page, (int)selectObjects.getTotalElements(), "/search?key="+key, pageSize);
		model.addAttribute("pages", pages);
		return "index/index";
	}
	/**
	 * 
	 * @Title: index
	 * @Description: 首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {

		long start = System.currentTimeMillis();
		System.out.println("访问开始时间:==================>" + start);

		// 只查询文章状态审核过的
		article.setStatus(1);
		article.setDeleted(0);// 未删除

		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		Thread t6 = null;
		Thread t7 = null;

		// 1.显示左侧栏目
		//定义线程
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				List<Channel> channels = channelService.selects();
				model.addAttribute("channels", channels);
			}
		});

		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 2.查询栏目下的分类
				if (null != article.getChannelId()) {

					List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
					model.addAttribute("categorys", categorys);

					// 3. 如果栏目不为空,并且分类也不为空则查询分类下的文章
					// if(null!=article.getCategoryId()) {
					PageInfo<ArticleWithBLOBs> info = articleService.selects(article, page, pageSize);

					String url = "/?channelId=" + article.getChannelId();
					if (null != article.getCategoryId()) {
						url += "&categoryId=" + article.getCategoryId();
					}

					String pages = PageUtil.page(page, info.getPages(), url, pageSize);

					model.addAttribute("articles", info.getList());
					model.addAttribute("pages", pages);
					// }

				}

			}
		});

		t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 4 如果栏目为空则默认查询热点文章
				if (null == article.getChannelId()) {
					article.setHot(1);// 热点文章
					article.setContentType(ArticleEnum.HTML.getCode());
					//从mysql中查询数据
					//用Redis优化热点文章的步骤
					
					//1.从Redis中查询热点文章用string类型
					List<ArticleWithBLOBs> redisArticleWithBLOBs = (List<ArticleWithBLOBs>) redisTemplate.opsForValue().get("hot_articles");
					//2.判断Redis是否有
					if (redisArticleWithBLOBs == null) {
						System.err.println("数据库中查询热点文章");
						//3.如果redis中没有,把从mysql中查的数据保存在redis(设置过期时间,1小时,或一天),反回给页面
						//4.先从mysql中查询前10条热点文章
						PageInfo<ArticleWithBLOBs> info = articleService.selects(article, page, pageSize);
						redisTemplate.opsForValue().set("hot_articles", info.getList(), 1,TimeUnit.HOURS);
						String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
						model.addAttribute("hotArticles", info.getList());
						model.addAttribute("pages", pages);
					}else {
						System.err.println("从reids中查询数据...");
						model.addAttribute("hotArticles", redisArticleWithBLOBs);
					}
				}

			}
		});

		// 封装查询条件
		model.addAttribute("article", article);

		t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 5.24小时热文

				Article article2 = new Article();
				article2.setHot(1);
				article2.setContentType(ArticleEnum.HTML.getCode());
				article2.setCreated(DateUtil.getDateByBefore());// 24小时之前的时间
				System.out.println("24xiaoshi+++++++++++++++++++"+article2.getCreated());
				PageInfo<ArticleWithBLOBs> info = articleService.selects(article2, page, pageSize);
				// 封装查询结果集
				model.addAttribute("article24", info.getList());
			}
		});

		t5 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 6.最新文章
				Article article3 = new Article();
				article3.setStatus(1);// 显示审过的文章
				article3.setContentType(ArticleEnum.HTML.getCode());
				PageInfo<ArticleWithBLOBs> info2 = articleService.selects(article3, page, pageSize);
				// 封装查询结果集
				model.addAttribute("articlehot", info2.getList());

			}
		});

		t6 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 图片集
				// 7.最新文章
				Article article4 = new Article();
				article4.setStatus(1);// 显示审过的文章

				article4.setContentType(ArticleEnum.IMAGE.getCode());

				PageInfo<ArticleWithBLOBs> info4 = articleService.selects(article4, page, pageSize);

				// 封装查询结果集
				model.addAttribute("articlepics", info4.getList());

			}
		});
		
		//专题
		t7 = new Thread(new Runnable() {

			@Override
			public void run() {
				List<Special> list = specialService.selects();
			
				model.addAttribute("specialList", list);
				for (Special special : list) {
					System.out.println(special.getSpecialArticles());
				}

			}
		});
		
		
		
		//启动线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		
		try {
			//让子线程都执行完,在执行主线程
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("访问结束时间:==================>" + end);

		System.out.println("访问用时:==================>" + (end - start));

		return "index/index";

	}

	/**
	 * 
	 * @Title: select
	 * @Description: 查看单个普通文章
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("select")
	public String select(Model model, Integer id) {

		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/article";
	}

	/**
	 * 
	 * @Title: select
	 * @Description: 查看图片集文章
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("selectpic")
	public String selectpic(Model model, Integer id) {

		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);

		String string = article.getContent();

		JsonArray array = new JsonParser().parse(string).getAsJsonArray();
		ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
		Gson gson = new Gson();
		for (JsonElement jsonElement : array) {
			ArticleVO vo = gson.fromJson(jsonElement, ArticleVO.class);
			list.add(vo);
		}
		model.addAttribute("title", article.getTitle());// 标题
		model.addAttribute("list", list);// 标题包含的 图片的地址和描述
		return "index/articlepic";
	}
}
