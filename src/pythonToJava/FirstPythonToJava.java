package pythonToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @Description: 
 * @author: YongpengYuan
 * @date: 2018-11-05
 */
public class FirstPythonToJava {
	
	// 执行python文件
	public void execPythonFile() {
		//此方法运行速度快，可以运行导入的第三方库
		//此处，我没找到中文乱码的解决方法
		String language = "python";
		String path = "./python/python_src/my_file_python.py";
		String name = "lisi";
		String age = "1";
		String sex = "man";
		String email = "ruiaixinan@sina.com";
		
		// 设置传递给python的参数
		String[] arguments = new String[] {language, path, name, age, sex, email};
		
		try {
			Process process = Runtime.getRuntime().exec(arguments);
		
			BufferedReader buffData = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null; 
			while ((line = buffData.readLine()) != null) {
				System.out.println(line);	
			}  
			buffData.close();
			
			try {
				//检查是否成功运行
				int re = process.waitFor();
				System.out.println(re);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void execPythonMethod() {
		// 这种方法运行速度比较慢，不可以使用第三方库
		
		// 建立解析器
		PythonInterpreter pinter = new PythonInterpreter();
		// exec执行python代码
		pinter.exec("print('python to java')");
		
		// execfile执行python文件
		pinter.execfile("./python/python_src/my_method_python.py");
		
		// 获取文件中的函数
		PyFunction function = (PyFunction)pinter.get("say_information",PyFunction.class);
		String name = "王五";
		int age = 1;
		String sex = "男";
		String email="ruiaixinan@sin.com";
		
		//设置中文编码方式
		PyString PyName=Py.newStringUTF8(name);
		//如果不设置中文编码方式，name中有中文，则下面的代码会报错
		//PyString PyName=new PyString(name);
		
		PyString PySex=Py.newStringUTF8(sex);
		PyInteger Pyage=new PyInteger(age);
		PyString PyEmail=new PyString(email);
		
		//调用Python中的方法
		PyObject pyobject = function.__call__(PyName, Pyage, PySex, PyEmail);
		
		try {
			// 设置对返回的中文解码，在python中不需要设置中文编码方式
			String result = new String(pyobject.toString().getBytes("iso8859-1"), "utf-8");
			System.out.println("Success = " + result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 执行python类
	public void execPythonClass() {
		// 这种方法运行速度比较慢，不可以使用第三方库
		
		// 建立解析器
		PythonInterpreter pinter = new PythonInterpreter();
		
		// execfile执行python文件
		pinter.execfile("./python/python_src/my_class_python.py");
		
		// python实例化对象名
		String pyEntityObjName = "person";
		// Python类名
		String pyObjName = "Person";
	    // 实例 Python对象，并初始化类参数
		pinter.exec(pyEntityObjName + "=" + pyObjName + "('zhaoliu', 2, 'woman')");
		
		//获取Python实例化对象
		PyObject pyObj = pinter.get(pyEntityObjName);
		
		//获得对象中的方法
		PyObject infor = pyObj.invoke("sayMyInformation");
		System.out.println(infor.toString());
		
		//调用用参数的方法，并设置中文编码
		pyObj.invoke("set_name",new PyObject[] {Py.newStringUTF8("赵六")});
		PyObject name = pyObj.invoke("get_name");
		
		try {
			String result = new String(name.toString().getBytes("iso8859-1"), "utf-8");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		FirstPythonToJava fptj = new FirstPythonToJava();
		System.out.println("----读取文件");
		fptj.execPythonFile();
		System.out.println("-----读取方法");
		fptj.execPythonMethod();
		System.out.println("-----读取类文件");
		fptj.execPythonClass();
	}
}
