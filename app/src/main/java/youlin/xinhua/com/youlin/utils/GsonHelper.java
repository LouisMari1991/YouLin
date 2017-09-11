package youlin.xinhua.com.youlin.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : ${xuemei}
 *     e-mail : 1840494174@qq.com
 *     time   : 2017/04/19
 *     desc   : Gson数据转化处理
 *     version: 1.0
 * </pre>
 */

public class GsonHelper {
  private static Gson       sGson       = new Gson();
  private static JsonParser sJsonParser = new JsonParser();

  private GsonHelper() {
  }

  /**
   * 将json数据转化为实体数据
   *
   * @param jsonData json字符串
   * @param entityClass 类型
   * @return 实体
   */
  public static <T> T convertEntity(String jsonData, Class<T> entityClass) {
    T entity = null;
    try {
      entity = sGson.fromJson(jsonData.toString(), entityClass);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      LogUtils.i(e.toString());
    }
    return entity;
  }

  /**
   * 将json数据转化为实体列表数据
   *
   * @param jsonData json字符串
   * @param entityClass 类型
   * @return 实体列表
   */
  public static <T> List<T> convertEntities(String jsonData, Class<T> entityClass) {
    List<T> entities = new ArrayList<>();
    try {
      JsonArray jsonArray = sJsonParser.parse(jsonData).getAsJsonArray();
      for (JsonElement element : jsonArray) {
        entities.add(sGson.fromJson(element, entityClass));
      }
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
    }
    return entities;
  }

  /**
   * 将 Object 对象转为 String
   *
   * @param jsonObject json对象
   * @return json字符串
   */
  public static String object2JsonStr(Object jsonObject) {
    return sGson.toJson(jsonObject);
  }
}
