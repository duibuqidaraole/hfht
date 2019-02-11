//package com.zc.mall.core;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.zc.sys.common.util.codeGenerate.HibernateCodeGenerate;
///**
// * 自动生成代码
// * 包含Entity、Model、Service、Controller、Dao（可选）
// *
// * 生成之前需要修改实体名称、数据库表名称
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationConfig.xml"})
//public class CodeGen {
//
//	@Test
//	public void generate() {
//	    //实体名称(生成之前需要修改)
//        String entityName = "goodsPromotion";
//
//        //实体对应的数据库表名称(生成之前需要修改)
//        String tableName = "goods_promotion";
//
//        //包名称(并非完整包名，只是包含Entiy、model、Convert等的父包名)(为空时自动取实体名称的小写)
//        String packageName = "goods";
//        //模块名称
//        String moduleName = "商品活动";
//        //模块代码
//        String dbModel = "G";//首字母大写
//        // 目录
//        String projectPackage = "com.zc.mall";
//        // 版本
//        String version = "1.0.0";
//        // 数据库连接
//        String dbUrl = "jdbc:mysql://localhost:3306/zc_mall";
//        // 用户名
//        String dbName = "root";
//        // 密码
//        String dbPwd = "root";
//
//        HibernateCodeGenerate gen = new HibernateCodeGenerate();
//        gen.setPackageName(packageName);
//        gen.setProjectPackage(projectPackage);
//        gen.setEntityName(entityName);
//        gen.setTableName(tableName);
//        gen.setModuleName(moduleName);
//        gen.setDbModel(dbModel);
//        gen.setVersion(version);
//        gen.setDbUrl(dbUrl);
//        gen.setDbName(dbName);
//        gen.setDbPwd(dbPwd);
//
//        gen.setIscontroller(true);
//        gen.setIsdao(true);
//        gen.setIsentity(true);
//        gen.setIsservice(true);
//
//        gen.generate();
//	}
//
//}
