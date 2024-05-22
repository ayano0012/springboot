package com.example.demo.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.SampleDao;
import com.example.demo.entity.EntForm;


/*第1回演習*/
/*@Controller
public class FormController {

	@RequestMapping("/sample")

	public String top(Model model) {
		model.addAttribute("title", "こんにちは！！");
		return "index";
	}

	@RequestMapping("/")

	public String top2(Model model) {
		model.addAttribute("title", "Hello　World");
		return "Helloindex";
	}
	

}*/


@Controller
public class FormController {

	/*第2回基礎編1*/
	
	@RequestMapping("/sample")
	public String sample(Model model) {
		model.addAttribute("message", "Hello World"); 
		return "index";
	}

	@RequestMapping("/form")
	public String form(Model model,Form form) {
		model.addAttribute("title","サンプルフォーム");
		return "form/input";
	}
	
	/*第2回基礎編２*/
	@RequestMapping("/confirm")
	
	public String confirm(@Validated Form form, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("title","入力ページ");
			return "form/input";
		}

		model.addAttribute("title","確認ページ");
		return "form/confirm";
	}
	
	//第3回
	
	//SampleDaoの用意
		private final SampleDao sampledao;

		@Autowired
		public FormController(SampleDao sampledao) {
			this.sampledao = sampledao;
		}

		//完了の処理
		@RequestMapping ("/complete")
		public String complete(Form form, Model model){
			EntForm entform = new EntForm();
			entform.setName(form.getName1());
			entform.setAge(form.getAge1());
			sampledao.insertDb(entform);
			return "form/complete";
		}
		

	}
	
