package com.xiaogua.comments.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * xls工具类
 */
public class XlsUtils {

  /**
   * 下载表格
   * @param response response
   * @param template 模板地址
   * @param key 模板内值名称
   * @param value 模板内值
   * @param fileName 生存文件名
   * @throws IOException IOException
   */
  public static void download(HttpServletResponse response, String template, String key, Object value, String fileName) throws IOException {
    InputStream in = XlsUtils.class.getClassLoader().getResourceAsStream(template);
    OutputStream out = null;

    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    response.setContentType("application/vnd..ms-excel;charset=UTF-8");
    out = response.getOutputStream();

    Context context = new Context();
    context.putVar(key, value);
    try {
      JxlsHelper.getInstance().processTemplate(in, out, context);
    } catch (Exception e) {

    }
  }
}
