package cn.org.rapid_framework.generator;


import java.io.File;

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
		generator.setTemplateRootDir(new File("/Users/xulei/IdeaProjects/lingyunProjectRapidFramework/src/main/resources/jpa_template"));
		generator.setOutRootDir("/Users/xulei/IdeaProjects/lingyunProjectRapidFramework/generator-output");
		GeneratorFacade g = new GeneratorFacade();
		g.setGenerator(generator);

		g.printAllTableNames();				//打印数据库中的表名称
		
		g.deleteOutRootDir();							//删除生成器的输出目录


		g.generateByMap();

		g.generateByAllTable();

		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRootDir"));
	}
}
