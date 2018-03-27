package youlin.xinhua.com.youlin.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/26
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class MyRule implements TestRule {

  @Override public Statement apply(final Statement base, final Description description) {

    return new Statement() {
      @Override public void evaluate() throws Throwable {
        // evaluate前执行方法相当于@Before
        String methodName = description.getMethodName();

        // 获取测试方法的名字
        System.out.println(methodName + "测试开始！");

        // 运行的测试方法
        base.evaluate();

        // evaluate后执行方法相当于@After
        System.out.println(methodName + "测试结束！");
      }
    };
  }
}
