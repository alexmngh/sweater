package com.example.sweater;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/* */
/**
 * The class уровня (слоя) Controller
 * Контроллер это модуль который по этому пути "/greeting" слушает запросы и обрабатывает их
 * <b>none</b>
 * @author  alex
 * @version 1.0.1
 */
@Slf4j
@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;
    /**
     * метод - чтения запросов
     * Аннотация @RequestMapping гарантирует, что HTTP запросы к /greeting приведут к выполнению метода greeting().
     * В этом примере не определено GET, PUT, POST и так далее, потому что @RequestMapping соответствует всем HTTP операциям
     * по умолчанию. @RequestMapping(method=GET) уточняет это соответствие.
     * @param @RequestParam связывает значение строкового параметра name запроса с name параметром метода greeting().
     * Этот параметр не required; если он отсутствует в запросе, то будет использовано defaultValue значение "World".
     * Значение параметра name добавлено в объект Model, что делает его доступным в шаблоне представления.
     * верисия для <artifactId>spring-boot-starter-mustache</artifactId>
     * @param name имя
     * @param model куда мы будем складывать данные для пользователя
     * @return возвращает имя файла который хотим оторазить : передаем в этот файл переменную name
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        log.debug("greeting ");

        model.put("name", name);
        return "greeting";
    }
    /*верисия для <artifactId>spring-boot-starter-thymeleaf</artifactId>
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
     */
    /**
     * метод - чтения запросов
     * Аннотация @GetMapping гарантирует, что HTTP запросы приведут к выполнению метода main().
     * @param model куда мы будем складывать данные для пользователя
     * @return возвращает имя файла который хотим отобразить,
     * т.е. этот метод выполняется при отображении этой страницы : передаем в этот файл переменную name
     * т.к. mapping не указываем то это означает, что запросы будут отправлены на туже страницу от куда пришло сообщение
     */
    @GetMapping
    public String main(Map<String, Object> model){
        log.debug("main ");
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    /**
     * метод - отправки запросов
     * Аннотация @RequestParam берет значение (в нашем случае из формы main.mustage), а может брать из URL
     * @param model куда мы будем складывать данные для пользователя
     * @return возвращает имя файла который хотим отобразить,
     * т.к. mapping не указываем то это означает, что запросы будут отправлены на туже страницу от куда пришло сообщение
     */
    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        log.debug("add ");
        System.out.println("add");

        //сохранили
        Message message = new Message(text, tag);
        messageRepo.save(message);
        //взяли из репозитория, положили в model и отдали пользователю
         Iterable<Message> messages = messageRepo.findAll();
         model.put("messages", messages);

        return "main";
    }
}