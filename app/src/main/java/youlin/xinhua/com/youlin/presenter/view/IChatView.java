package youlin.xinhua.com.youlin.presenter.view;

import com.tencent.imsdk.TIMMessage;
import java.util.List;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/07
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public interface IChatView {

  /**
   * 显示消息
   */
  void showMessage(List<TIMMessage> messages);


}
