package cn.org.rapid_framework.generator;


import java.io.File;
import java.util.Map;

/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		Generator generator = new Generator();
		generator.setTemplateRootDir(new File("templates/template"));
		generator.setOutRootDir("generator-output");
		GeneratorFacade facade = new GeneratorFacade();
		facade.setGenerator(generator);
		facade.deleteOutRootDir();
//



		GeneratorProperties.setProperty("moduleName","life");
		GeneratorProperties.setProperty("basepackage","com.lingyun.projects");
		GeneratorProperties.setProperty("tableRemovePrefixes","test");

		GeneratorProperties.setProperty("jdbc_url","jdbc:h2:~/test");
		GeneratorProperties.setProperty("jdbc_driver","org.h2.Driver");
		GeneratorProperties.setProperty("jdbc_username","sa");
		GeneratorProperties.setProperty("outRoot","generator-output");

		facade.generateByTable("test_user");

		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
