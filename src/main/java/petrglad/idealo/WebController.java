package petrglad.idealo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    @GetMapping("/")
    String getPage() {
        return "main";
    }

    @GetMapping("/style.css")
    @ResponseBody
    Resource getStylesheet() {
        return new ClassPathResource("static/style.css");
    }

    @PostMapping("/run-script")
    String runScript(Model model, String script) {
        final var interpreter = new RobotScript();
        final var robot = interpreter.run(script);
        model.addAttribute("robot", robot);
        return "main";
    }
}
