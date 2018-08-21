package com.krm.report.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	// 文件路径+名称
	private static String filenameTemp;

	/**
	 * 向文件中写入内容
	 * 
	 * @param filepath
	 *            文件路径与名称
	 * @param newstr
	 *            写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, String newstr)
			throws IOException {
		Boolean bool = false;
//		String filein = newstr + "\r\n";// 新写入的行，换行
//		String temp = "";

//		FileInputStream fis = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//		FileOutputStream fos = null;
//		PrintWriter pw = null;
		BufferedWriter out = null;
		try {
			File sfile = new File("");
			String absolutePath = sfile.getAbsolutePath();
			log.info("***Read filepath***:" + absolutePath);
			String parent = absolutePath.substring(0,
					absolutePath.lastIndexOf("/"));
			log.info("***Read filepath***:" + parent);
			File file = new File(parent + filepath);// 文件路径(包括文件名称)
			log.info("***Read filepath***:" + parent + filepath);
			if (!file.exists()) {
				log.info("***Create file ***:" + parent + filepath);
				file.createNewFile();
			}
			synchronized (FileUtil.class) {
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file, true)));
				out.write(newstr + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// try {
		// File sfile = new File("");
		// String absolutePath = sfile.getAbsolutePath();
		// log.info("***Create filepath***:" + absolutePath);
		// String parent = absolutePath.substring(0,
		// absolutePath.lastIndexOf("/"));
		// log.info("***Create filepath***:" + parent);
		// File file = new File(parent + filepath);// 文件路径(包括文件名称)
		// log.info("***Create filepath***:" + parent + filepath);
		// if (!file.exists()) {
		// log.info("***Create file ***:" + parent + filepath);
		// file.createNewFile();
		// }
		// // 将文件读入输入流
		// fis = new FileInputStream(file);
		// isr = new InputStreamReader(fis);
		// br = new BufferedReader(isr);
		// StringBuffer buffer = new StringBuffer();
		//
		// // 文件原有内容
		// for (int i = 0; (temp = br.readLine()) != null; i++) {
		// buffer.append(temp);
		// // 行与行之间的分隔符 相当于“\n”
		// buffer = buffer.append(System.getProperty("line.separator"));
		// }
		// synchronized (FileUtil.class) {
		// buffer.append(filein);
		// }
		//
		// fos = new FileOutputStream(file);
		// pw = new PrintWriter(fos);
		// pw.write(buffer.toString().toCharArray());
		// pw.flush();
		// bool = true;
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally {
		// // 不要忘记关闭
		// if (pw != null) {
		// pw.close();
		// }
		// if (fos != null) {
		// fos.close();
		// }
		// if (br != null) {
		// br.close();
		// }
		// if (isr != null) {
		// isr.close();
		// }
		// if (fis != null) {
		// fis.close();
		// }
		// }
		return bool;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static boolean delFile(String fileName) {
		Boolean bool = false;
		filenameTemp = fileName + ".txt";
		File file = new File(filenameTemp);
		try {
			if (file.exists()) {
				file.delete();
				bool = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bool;
	}

	public static String getFileLastLine(String fileName) {
		String filePath = FileUtil.class.getClassLoader().getResource(fileName)
				.getPath();// 获取文件路径
		log.info("***dayReportExportSuccessOrgan.txt***:" + filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.info("***filePath not exists:" + filePath + "***");
			return "";
		}
		BufferedReader reader = null;
		String temp = null;
		int line = 1;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}

	public static String getMaxDate(String fileName) {
		String filePath = FileUtil.class.getClassLoader().getResource(fileName)
				.getPath();// 获取文件路径
		log.info("***" + fileName + "***:" + filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.info("***filePath not exists:" + filePath + "***");
			return "";
		}
		BufferedReader reader = null;
		String temp = null;
		String maxDate = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
				log.info("***file line content***:" + temp);
				String date = getSucessExportDate(temp);
				if (date.compareTo(maxDate) > 0) {
					maxDate = date;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return maxDate;
	}

	/**
	 * 获取已经成功导出的日期机构信息
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> getFileLine(String fileName) {
		List<String> lineList = new ArrayList<String>();
		String filePath = FileUtil.class.getClassLoader().getResource(fileName).getPath();// 获取文件路径
		log.info("***" + fileName + "***:" + filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.info("***filePath not exists:" + filePath + "***");
			return null;
		}
		BufferedReader reader = null;
		String temp = null;
		String maxDate = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
//				log.info("***file line content***:" + temp);
				lineList.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return lineList;
	}

	public static List<String> getOrganList(String fileName) {
		String filePath = FileUtil.class.getClassLoader().getResource(fileName)
				.getPath();// 获取文件路径
		log.info("***+fileName+***:" + filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.info("***filePath not exists:" + filePath + "***");
			return null;
		}
		List<String> organList = new ArrayList<String>();
		BufferedReader reader = null;
		String temp = null;
		int line = 1;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
				log.info("***file line content***:" + temp);
				String organ = getSucessExportOrgan(temp);
				organList.add(organ);
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return organList;
	}

	public static String getSucessExportDate(String line) {
		if (line != null && !line.equals("")) {
			if (line.indexOf("\\|") == -1) {
				return "";
			}
			String[] data = line.split("\\|");
			for (String string : data) {
				log.info("***" + string);
			}
			String result = "";
			try {
				if (line.indexOf("\\:") == -1) {
					return "";
				}
				result = data[0].split(":")[1];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error(e.getMessage());
			}
			return result;
		}
		return "";
	}

	public static String getSucessExportOrgan(String line) {
		if (line != null && !line.equals("")) {
			if (line.indexOf("\\|") == -1) {
				return "";
			}
			String[] data = line.split("\\|");
			for (String string : data) {
				log.info("***" + string);
			}
			String result = "";
			try {
				if (line.indexOf("\\:") == -1) {
					return "";
				}
				result = data[1].split(":")[1];
			} catch (Exception e) {
				// e.printStackTrace();
				log.error(e.getMessage());
			}
			return result;
		}
		return "";
	}

	public static void main(String[] args) {
		// getFileLastLine("dayReportExpo|rtSuccessOrgan.txt");
		// String aString = "2012-01-01";
		String bString = "aaa|aaaaaaaa";
		// System.out.println(aString.compareTo(bString)>0);
		String aa[] = bString.split("\\|");
		System.out.println(aa[0] + "," + aa[1]);
	}

	public static boolean isExport(List<String> lineList, String date,
			String organ) {
		boolean flag = false;
		if(lineList==null){
			return flag;
		}
		for (String string : lineList) {
			if (string.contains(date) && string.contains(organ)) {
				log.info("***already exported report" + string);
				return true;
			}
		}
		return flag;
	}

}
