package common;

public class HtmlGenerator {
    public static String getMessagePage(String message,String nextUrl ) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"utf-8\">");
        stringBuilder.append("<title>提示页面</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");

        stringBuilder.append("<h3>");
        stringBuilder.append(message);
        stringBuilder.append("</h3>");

        stringBuilder.append(String.format("<a href=\"%s\"> 点击这里进行跳转 </a>",nextUrl));
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();

    }


}
