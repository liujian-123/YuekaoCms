package com.liujian.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liujian.cms.domain.Article;
import com.liujian.cms.domain.ArticleWithBLOBs;
import com.liujian.cms.domain.Special;
import com.liujian.cms.domain.User;
import com.liujian.cms.service.ArticleService;
import com.liujian.cms.service.SpecialService;
import com.liujian.cms.service.UserService;
import com.liujian.cms.util.PageUtil;
import com.liujian.utils.StringUtil;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员后台
 * @author: liujian
 * @date: 2019年10月15日 下午4:04:
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private SpecialService specialService;
	
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入后台首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"index","","/"})
	public String index() {
		
		return "admin/index";
		
	}
	/**
	 * 
	 * @Title: specials 
	 * @Description: 专题列表
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("special/selects")
	public String specials(Model model) {
		List<Special> list = specialService.selects();
		model.addAttribute("specials", list);
		return "admin/specials";
	}
	
	
	
	
	
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 增加专题
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("special/add")
	public String add() {
		 
		return "admin/specialsadd";
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 增加专题
	 * @param model
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("special/add")
	public boolean add(Special special,Model model) {
		
		if (StringUtil.hasText(special.getTitle())) {
			return specialService.insert(special)>0;
		}else {
//			model.addAttribute("error", "专题标题必须有值");
			return false;
		}
		
		
	}
	/**
	 *  
	 * @Title: select 
	 * @Description: 单个专题
	 * @param model
	 * @param sid
	 * @return
	 * @return: String
	 */
	@GetMapping("special/select")
	public String select(Model model ,Integer sid) {
		Special special = specialService.select(sid);
		model.addAttribute("s", special);
		return "admin/special";
	}
	/**
	 * 
	 * @Title: addArticle 
	 * @Description: 为专题增加文章
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("/special/addArticle")
	public boolean addArticle(Integer sid,Integer aid) {
		return specialService.insertSpecialArticle(sid, aid)>0;
	}
	
	/** 
	 * @Title: update 
	 * @Description: 根据前台传过来的值获取到某一个专题
	 * @param model
	 * @param sid
	 * @return
	 * @return: String
	 */
	@GetMapping("special/update")
	public String update(Model model ,Integer sid) {
		Special special = specialService.getByid(sid);
		model.addAttribute("s", special);
		return "admin/updatespecial";
	}
	/** 
	 * @Title: updatespecial 
	 * @Description: 修改专题
	 * @param special
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("/special/updatespecial")
	public boolean updatespecial(Special special) {
		return specialService.update(special)>0;
	}
	
	
	/**
	 *  
	 * @Title: users 
	 * @Description: 文章列表
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("articles")
	public String users(Model model,Article article ,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		 //默认待审
		if(article.getStatus()==null)
		 article.setStatus(0);//待审
		
		PageInfo<ArticleWithBLOBs> info = articleService.selects(article, page, pageSize);
		//调用分页工具
		String pages = PageUtil.page(page, info.getPages(), "/admin/articles?title="+article.getTitle()+"&status="+article.getStatus(), pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("pages", pages);
		model.addAttribute("info", info);
		
		return "admin/articles";
		
	}
	
	/**
	 * 
	 * @Title: article 
	 * @Description: 单个文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("article")
	public String article(Model model,Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article",article);
		return "admin/article";
		
	}
	
	/**
	 * 设置为热门文章
	 * @Title: updateArticle 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("updateArticle")
	public boolean updateArticle(ArticleWithBLOBs article) {
		return articleService.updateByPrimaryKeySelective(article)>0;
	}
	
   /**
    * 
    * @Title: users 
    * @Description: 用户列表
    * @param model
    * @param username
    * @param page
    * @param pageSize
    * @return
    * @return: String
    */
	@GetMapping("users")
	public String users(Model model,@RequestParam(defaultValue = "")String username ,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		
		PageInfo<User> info = userService.selects(username, page, pageSize);
		//调用分页工具
		String pages = PageUtil.page(page, info.getPages(), "/admin/users?username="+username, pageSize);
		
		model.addAttribute("users", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		return "admin/users";
		
	}
	
	/**
	 * 修改用户状态
	 * @Title: updateUser 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateUser")
	public boolean updateUser(User user) {
		
		return userService.updateByPrimaryKeySelective(user)>0;
	}
}
