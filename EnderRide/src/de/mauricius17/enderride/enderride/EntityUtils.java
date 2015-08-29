package de.mauricius17.enderride.enderride;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_8_R3.EntityTypes;

public class EntityUtils {

	public static boolean registered(int paramInt) {
        if (EntityTypes.a(paramInt) != null) {
            return true;
        }
        return false;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void register(String paramString, int paramInt, Class<?> paramClass) {
        try {
            Field c = ReflectionUtils.getField("c", EntityTypes.class);
            ((Map) c.get(null)).put(paramString, paramClass);

            Field d = ReflectionUtils.getField("d", EntityTypes.class);
            ((Map) d.get(null)).put(paramClass, paramString);

            Field e = ReflectionUtils.getField("e", EntityTypes.class);
            ((Map)e.get(null)).put(Integer.valueOf(paramInt), paramClass);

            Field f = ReflectionUtils.getField("f", EntityTypes.class);
            ((Map)f.get(null)).put(paramClass, Integer.valueOf(paramInt));

            Field g = ReflectionUtils.getField("g", EntityTypes.class);
            ((Map)g.get(null)).put(paramString, Integer.valueOf(paramInt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
