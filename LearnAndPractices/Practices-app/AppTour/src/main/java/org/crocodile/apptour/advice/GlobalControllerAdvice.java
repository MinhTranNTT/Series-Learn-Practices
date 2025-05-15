package org.crocodile.apptour.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.crocodile.apptour.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    // 1. Sử dụng Model, không có giá trị trả về, đưa vào Model với key và value tùy chỉnh
    @ModelAttribute
    public void modelInfo(Model model) {
        // log.error("Gán dữ liệu toàn cục: ModelAttribute");
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "XiaoLan");
        map.put("age", "10");
        model.addAttribute("mapList", map);
    }

    // 2. Không chỉ định name, trả về ModelMap
    @ModelAttribute()
    public Map<String, String> presetParam2(ModelMap modelMap) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "XiaoLan");
        map.put("age", "10");
        modelMap.addAttribute("modelMap", map);
        return map;
    }

    // 3. Chỉ định name, trả về map
    @ModelAttribute(name = "map3")
    public Map<String, String> presetParam3() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "XiaoLan");
        map.put("age", "10");
        return map;
    }

    // 4. Chấp nhận tham số từ request
    // @ModelAttribute()
    // public void presetParam4(@RequestParam("name") String name, Model model) {
    //     model.addAttribute("name", name);
    // }

    // ================= @InitBinder - Tiền xử lý dữ liệu toàn cục =================

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.error("Tiền xử lý dữ liệu toàn cục: InitBinder");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        // Định dạng ngày tháng
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Xử lý ngoại lệ toàn cục: Exception");
        return Result.fail(e.getMessage());
    }

}
