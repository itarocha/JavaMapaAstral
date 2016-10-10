package br.itarocha.carta;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap; 
	
public class Mapeador {
	
	private Map<Class<?>,Class<?>> mapa = new HashMap<Class<?>,Class<?>>();

	public void load(String nomeArquivo) throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream(nomeArquivo));
		
		for (Object k : prop.keySet()){
			System.out.println(k.toString());
			
			Class<?> itf = Class.forName(k.toString());
			Class<?> impl = Class.forName(prop.get(k).toString());
			
			if (!itf.isInterface()){
				throw new RuntimeException(k.toString() + " não é uma interface");
			}
			if (!itf.isAssignableFrom(impl)){
				throw new RuntimeException("A classe "+impl.getName()+" não implementa "+itf.getName());
			}
			
			mapa.put(itf, impl);
		}
		
	}
	
	public Class<?> getImplementacao(Class<?> itf){
		return mapa.get(itf);
	}
	
	public <E> E getInstancia(Class<E> itf) throws Exception{
		Class<?> impl = mapa.get(itf);
		return (E) impl.newInstance();
	}
	
	public <E> E getInstancia(Class<E> itf, Object... params ) throws Exception{
		Class<?> impl = mapa.get(itf);
		Class<?>[] tiposParametros = new Class<?>[params.length];
		for(int i=0; i < tiposParametros.length; i++){
			tiposParametros[i] = params.getClass();
		}
		Constructor<?> c = impl.getConstructor(tiposParametros);
		return (E) impl.newInstance();
	}
	
}
