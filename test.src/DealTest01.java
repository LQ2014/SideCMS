import java.lang.reflect.Method;

import com.side.util.annotation.Deal;


public class DealTest01 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		String className = "LoginAction";
		Class clazz = Class.forName(className);
		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Deal.class)) {
				Deal deal = method.getAnnotation(Deal.class);
				System.out.println(deal.path());
				System.out.println(deal.error());
				System.out.println(deal.type());
				System.out.println(deal.forward());
			}
		}
	}

}
