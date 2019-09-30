package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.domain.ding.DingTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marvel on 2019/9/24.
 */
@Controller
@RequestMapping("/ding")
public class DingViewController {

    @GetMapping("/dingTask/{id}/config")
    public String findDingTaskConfigById(ModelMap modelMap, @PathVariable Long id) {
        DingTask dingTask = new DingTask();
        dingTask.setId(id);
        dingTask.setName("weiweicat" + id);
        modelMap.addAttribute("dingTask", dingTask);
        return "ding/dingTaskConfig";
    }
}
