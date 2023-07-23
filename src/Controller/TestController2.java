package Controller;

import Annotation.Controller;
import Annotation.RequestMapping;

@Controller
@RequestMapping("test2")
public class TestController2 {
	@RequestMapping("index2")
	public void index2() {
		System.out.println("test2->index2");
	}
}
