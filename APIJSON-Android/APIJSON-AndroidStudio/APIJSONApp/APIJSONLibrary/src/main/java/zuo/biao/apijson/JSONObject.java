/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.apijson;

import java.util.List;
import java.util.Map;

/**use this class instead of com.alibaba.fastjson.JSONObject
 * @author Lemon
 * @see #put
 * @see #puts
 * @see #putsAll
 */
public class JSONObject extends com.alibaba.fastjson.JSONObject {
	private static final long serialVersionUID = 1L;
	
	private static final String TAG = "JSONObject";

	
	/**ordered
	 */
	public JSONObject() {
		super(true);
	}
	/**transfer Object to JSONObject
	 * @param object
	 * @see {@link #JSONObject(Object, boolean)}
	 */
	public JSONObject(Object object) {
		this(toJSONString(object));
	}
	/**parse JSONObject with JSON String
	 * @param json
	 * @see {@link #JSONObject(String, boolean)}
	 */
	public JSONObject(String json) {
		this(parseObject(json));
	}
	/**transfer com.alibaba.fastjson.JSONObject to JSONObject
	 * @param object
	 * @see {@link #putsAll(com.alibaba.fastjson.JSONObject)}
	 */
	public JSONObject(com.alibaba.fastjson.JSONObject object) {
		this();
		putsAll(object);
	}




	//judge <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public static final String KEY_ARRAY = "[]";

	/**判断是否为Array的key
	 * @param key
	 * @return
	 */
	public static boolean isArrayKey(String key) {
		return key != null && key.endsWith(KEY_ARRAY);
	}
	/**判断是否为对应Table的key
	 * @param key
	 * @return
	 */
	public static boolean isTableKey(String key) {
		return StringUtil.isBigName(key);
	}
	//judge >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//JSONObject内关键词 key <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	
	public static final String KEY_ID = "id";
	public static final String KEY_ID_IN = KEY_ID + "{}";

	/**set "id":id in Table layer
	 * @param id
	 * @return
	 */
	public JSONObject setId(Long id) {
		return puts(KEY_ID, id);
	}
	/**set id{}:[] in Table layer
	 * @param list
	 * @return
	 */
	public JSONObject setIdIn(List<Object> list) {
		return puts(KEY_ID_IN, list);
	}
	
	
	//@key关键字都放这个类 <<<<<<<<<<<<<<<<<<<<<<
	public static final String KEY_ROLE = "@role"; //角色，拥有对某些数据的某些操作的权限
	public static final String KEY_CONDITION = "@condition"; //条件 TODO 用 @where& @where| @where! 替代？
	public static final String KEY_TRY = "@try"; //尝试，忽略异常
	public static final String KEY_DROP = "@drop"; //丢弃，不返回
	public static final String KEY_CORRECT = "@correct"; //字段校正
	
	public static final String KEY_SCHEMA = "@schema"; //数据库，Table在非默认schema内时需要声明
	public static final String KEY_ABOUT = "@about"; //关于，返回数据库表的信息，包括表说明和字段说明
	public static final String KEY_COLUMN = "@column"; //查询的Table字段或SQL函数
	public static final String KEY_GROUP = "@group"; //分组方式
	public static final String KEY_HAVING = "@having"; //聚合函数条件，一般和@group一起用
	public static final String KEY_ORDER = "@order"; //排序方式
	//@key关键字都放这个类 >>>>>>>>>>>>>>>>>>>>>>


	/**set role of request sender
	 * @param role
	 * @return this
	 */
	public JSONObject setRole(String role) {
		return puts(KEY_ROLE, role);
	}
	
	/**set try, ignore exceptions
	 * @param tri
	 * @return this
	 */
	public JSONObject setTry(boolean tri) {
		return puts(KEY_TRY, tri);
	}
	
	/**set drop, data dropped will not return
	 * @param drop
	 * @return this
	 */
	public JSONObject setDrop(boolean drop) {
		return puts(KEY_DROP, drop);
	}
	
	/**set correct, correct keys to target ones
	 * @param correct  Map{originKey, [posibleKeys]}， posibleKey之间用 , 隔开
	 * @return this
	 */
	public JSONObject setCorrect(Map<String, String> correct) {
		return puts(KEY_CORRECT, correct);
	}
	
	

	/**set schema where table was puts
	 * @param schema
	 * @return this
	 */
	public JSONObject setSchema(String schema) {
		return puts(KEY_SCHEMA, schema);
	}

	/**set about
	 * @param about
	 * @return this
	 */
	public JSONObject setAbout(boolean about) {
		return puts(KEY_ABOUT, about);
	}

	/**set keys need to be returned
	 * @param keys  key0, key1, key2 ...
	 * @return {@link #setColumn(String)}
	 */
	public JSONObject setColumn(String... keys) {
		return setColumn(StringUtil.getString(keys, true));
	}
	/**set keys need to be returned
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setColumn(String keys) {
		return puts(KEY_COLUMN, keys);
	}

	/**set keys for group by
	 * @param keys key0, key1, key2 ...
	 * @return {@link #setGroup(String)}
	 */
	public JSONObject setGroup(String... keys) {
		return setGroup(StringUtil.getString(keys, true));
	}
	/**set keys for group by
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setGroup(String keys) {
		return puts(KEY_GROUP, keys);
	}

	/**set keys for having
	 * @param keys count(key0) > 1, sum(key1) <= 5, function2(key2) ? value2 ...
	 * @return {@link #setHaving(String)}
	 */
	public JSONObject setHaving(String... keys) {
		return setHaving(StringUtil.getString(keys, true));
	}
	/**set keys for having
	 * @param keys  "key0,key1,key2..."
	 * @return
	 */
	public JSONObject setHaving(String keys) {
		return puts(KEY_HAVING, keys);
	}

	/**set keys for order by
	 * @param keys  key0, key1+, key2- ...
	 * @return {@link #setOrder(String)}
	 */
	public JSONObject setOrder(String... keys) {
		return setOrder(StringUtil.getString(keys, true));
	}
	/**set keys for order by
	 * @param keys  "key0,key1+,key2-..."
	 * @return
	 */
	public JSONObject setOrder(String keys) {
		return puts(KEY_ORDER, keys);
	}


	//JSONObject内关键词 key >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//Request <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**
	 * @param key
	 * @param keys  path = keys[0] + "/" + keys[1] + "/" + keys[2] + ...
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsPath(String key, String... keys) {
		return puts(key+"@", StringUtil.getString(keys, "/"));
	}

	/**
	 * @param key
	 * @param isNull
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsNull(String key, boolean isNull) {
		return puts(key+"{}", SQL.isNull(isNull));
	}
	/**
	 * trim = false
	 * @param key
	 * @param isEmpty
	 * @return {@link #putsEmpty(String, boolean, boolean)}
	 */
	public JSONObject putsEmpty(String key, boolean isEmpty) {
		return putsEmpty(key, isEmpty, false);
	}
	/**
	 * @param key
	 * @param isEmpty
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsEmpty(String key, boolean isEmpty, boolean trim) {
		return puts(key+"{}", SQL.isEmpty(key, isEmpty, trim));
	}
	/**
	 * @param key
	 * @param compare <=0, >5 ...
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsLength(String key, String compare) {
		return puts(key+"{}", SQL.length(key) + compare);
	}

	/**设置搜索
	 * type = SEARCH_TYPE_CONTAIN_FULL
	 * @param key
	 * @param value
	 * @return {@link #putsSearch(String, String, int)}
	 */
	public JSONObject putsSearch(String key, String value) {
		return putsSearch(key, value, SQL.SEARCH_TYPE_CONTAIN_FULL);
	}
	/**设置搜索
	 * @param key
	 * @param value
	 * @param type
	 * @return {@link #puts(String, Object)}
	 */
	public JSONObject putsSearch(String key, String value, int type) {
		return puts(key+"$", SQL.search(value, type));
	}

	//Request >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	

	/**puts key-value in object into this
	 * @param object
	 * @return this
	 */
	public JSONObject putsAll(Map<? extends String, ? extends Object> map) {
		putAll(map);
		return this;
	}
	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		if (map != null && map.isEmpty() == false) {
			super.putAll(map);
		}
	}


	
	/**put and return this
	 * @param value  must be annotated by {@link MethodAccess}
	 * @return {@link #puts(String, boolean)}
	 */
	public JSONObject puts(Object value) {
		return puts(null, value);
	}
	/**put and return this
	 * @param key
	 * @param value 
	 * @return this
	 * @see {@link #put(String, Object)}
	 */
	public JSONObject puts(String key, Object value) {
		put(key, value);
		return this;
	}
	
	/**put and return value
	 * @param value  must be annotated by {@link MethodAccess}
	 * @return {@link #put(String, boolean)}
	 */
	public Object put(Object value) {
		return put(null, value);
	}
	/**put and return value
	 * @param key  StringUtil.isEmpty(key, true) ? key = value.getClass().getSimpleName();
	 * @param value
	 * @return value
	 */
	@Override
	public Object put(String key, Object value) {
		if (value == null) {
			Log.e(TAG, "put  value == null >> return null;");
			return null;
		}
		if (StringUtil.isEmpty(key, true)) {
			Class<?> clazz = value.getClass();
			if (clazz == null || clazz.getAnnotation(MethodAccess.class) == null) {
				throw new IllegalArgumentException("puts  StringUtil.isNotEmpty(key, true) == false" +
						" && clazz == null || clazz.getAnnotation(MethodAccess.class) == null" +
						" \n key为空时仅支持 类型被@MethodAccess注解 的value !!!" +
						" \n 如果一定要这么用，请对 " + clazz.getName() + " 注解！" +
						" \n 如果是类似 key[]:{} 结构的请求，建议用 putsAll(...) ！");
			}
			key = value.getClass().getSimpleName();
		}
		return super.put(key, value);
	}


	
}
