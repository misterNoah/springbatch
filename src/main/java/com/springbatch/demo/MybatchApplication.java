package com.springbatch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatchApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		MybatchApplication.RunFTF();
		MybatchApplication.RunFTD();
		MybatchApplication.RunDTF();
        MybatchApplication.RunDTD();
	}
	
	@SuppressWarnings("resource")
	public static void RunFTF() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("batch_FTF.xml");
        JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("FTFJOB");
        //构建参数
//	        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        //inputFile是配置文件中的value="file:#{jobParameters['inputFile']}
//	        JobParameters jobParameters = jobParametersBuilder.addString("inputFile", "D:\\WorkSpace\\Mybatch\\src\\main\\resources\\inputFile.csv").toJobParameters();
        long start = System.currentTimeMillis();
        try {
            launcher.run(job, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("---耗时：" + (end - start) / 1000 + "秒");
    }
	 
	 /**
     * 启动  文件>>数据库
     * 注意：此处读取的文件是绝对路径，请修改为你那边的路径
     * 数据库信息在配置文件里，建表语句在Resources里
     */
	@SuppressWarnings("resource")
    public static void RunFTD() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("batch_FTD.xml");
        JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("FTDJOB");
        //构建参数
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        //inputFile是配置文件中的value="file:#{jobParameters['inputFile']}
        JobParameters jobParameters = jobParametersBuilder.addString("inputFile", "D:\\SpringBootDemo\\springbatch-demo2\\src\\main\\resources\\inputFile.csv").toJobParameters();
        long start = System.currentTimeMillis();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("---耗时：" + (end - start) / 1000 + "秒");
    }
	
	
	/**
     * 启动  数据库>>>文件
     */
    @SuppressWarnings("resource")
	public static void RunDTF() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("batch_DTF.xml");
        JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("DTFJOB");
        //构建参数
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        //sql 是配置文件中的<property name="sql" value="#{jobParameters['sql']}"/>  已经测试使用select * 也可以，不需要字段
        JobParameters jobParameters = jobParametersBuilder.addString("sql", "select * from product").toJobParameters();
        long start = System.currentTimeMillis();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("---耗时：" + (end - start) / 1000 + "秒");
    }
    
    
    /**
     * 启动  数据库>>>数据库
     * 注意新旧2个数据库的配置和使用JdbcTemplate 装载的不同
     */
    @SuppressWarnings("resource")
	public static void RunDTD() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("batch_DTD.xml");
        JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("DTDJOB");
        //构建参数
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        //sql 是配置文件中的<property name="sql" value="#{jobParameters['sql']}"/>  已经测试使用select * 也可以，不需要字段
        JobParameters jobParameters = jobParametersBuilder.addString("sql", "select id,name,description,quantity from product where id < 4000").toJobParameters();
        long start = System.currentTimeMillis();
        try {
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("---耗时：" + (end - start) / 1000 + "秒");
    }

}
