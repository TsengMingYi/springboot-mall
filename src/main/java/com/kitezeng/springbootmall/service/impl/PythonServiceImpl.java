package com.kitezeng.springbootmall.service.impl;

import com.kitezeng.springbootmall.service.PythonService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;

//@EnableAsync
@Component
public class PythonServiceImpl implements PythonService {

    private String pythonInterpreterPath = "src/main/java/com/kitezeng/springbootmall/python";
    //private String pythonInterpreterPath = "/Users/zengmingyi/IdeaProjects/springboot-mall/src/main/java/com/kitezeng/springbootmall/python";
    //private String pythonFilePath = "/Users/zengmingyi/IdeaProjects/springboot-mall/src/main/resources/application.py";
    private String pythonFilePath = "src/main/resources/pyfiles/application.py";
    //private ClassPathResource resource = new ClassPathResource("com/kitezeng/springbootmall/python");
    //private ClassPathResource resource1 = new ClassPathResource("application.py");
//    private String[] arguments = new String[]{getFilesPath("python","python"), getFilesPath("application.py","application.py")};
    //private String[] arguments = new String[]{pythonInterpreterPath,resource1.getURL().toString()};
    private Process process;

    @Override
    public String usePython() {
        StringBuilder result = new StringBuilder();
        ProcessBuilder pb = null;

//        try {
            ClassPathResource resource1 = new ClassPathResource("application.py");
//            ClassPathResource resource = new ClassPathResource("python");
//        String data = "";
//        ClassPathResource cpr = new ClassPathResource("application.py");
//        try {
//            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
//            data = new String(bdata, StandardCharsets.UTF_8);
//            System.out.println(data);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }


//        try {
            pb = new ProcessBuilder("python3", getFilesPath("application.py", "application.py"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        pb.redirectErrorStream(true); // redirect error stream to a standard output stream
        Process process = null; // Used to start the process
        try {
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads the output stream of the process.
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line; // this will be used to read the output line by line. Helpful in troubleshooting.
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result.toString();
    }

    @Override
    public byte[] file2Byte(File file) {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public byte[] getBytesByStream(InputStream inputStream) {
        byte[] bytes = new byte[1024];

        int b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while ((b = inputStream.read(bytes)) != -1) {

                byteArrayOutputStream.write(bytes, 0, b);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFilesPath(String fileName, String fileName1) {
        String filePath = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource(fileName);
            InputStream inputStream = classPathResource.getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
//生成临时文件,这个是生成在根目录下
            filePath = System.getProperty("user.dir") + "/" + fileName1;
//            filePath=System.getProperty("user.dir")+"xxxx.py";
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bdata);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.info(“copy文件失败:”+e.getMessage());
        }
        return filePath;
    }

    private String invokePy(String[] para) {
        StringBuilder result = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(para);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
