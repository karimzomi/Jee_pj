package view;

import java.util.List;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class CustomTableTag extends TagSupport {

    private String columns;

    private List<Object> data;

    @Override
    public int doStartTag() throws JspException {
        String[] columnsList = columns.split(",");
        StringBuilder table = new StringBuilder();
        table.append("<table class=\"table\">");
        table.append("<thead>");
        table.append("<tr>");
        for (String column : columnsList) {
            table.append("<th scope=\"col\">");
            table.append(column);
            table.append("</th>");
        }
        table.append("</tr>");
        table.append("</thead>");

        table.append("<tbody>");
        try {
            for (Object object : data) {
                table.append("<tr>");
                for (String column : columnsList) {
                    table.append("<td>");
                    table.append(object.getClass()
                            .getDeclaredMethod("get" + column.substring(0, 1).toUpperCase() + column.substring(1))
                            .invoke(object));
                    table.append("</td>");
                }
                table.append("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageContext.setAttribute("table", table.toString());
        JspWriter out = pageContext.getOut();
        try {
            out.println(table.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

}