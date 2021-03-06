package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class MainController {
    private static final Logger mylogger = LoggerFactory.getLogger(MainController.class);
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
     * @param model куда мы будем складывать данные для пользователя
     * @return возвращает имя файла который хотим оторазить : передаем в этот файл переменную name
     */
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "tmpgreeting";
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
        mylogger.debug("main ");
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
//        log.debug("add ");
        System.out.println("add prnt");

        //сохранили
        Message message = new Message(text, tag);
        messageRepo.save(message);
        //взяли из репозитория, положили в model и отдали пользователю
         Iterable<Message> messages = messageRepo.findAll();
         model.put("messages", messages);

        return "main";
    }

    /**
     * метод -
     * Аннотация @RequestParam берет значение (в нашем случае из формы main.mustage), а может брать из URL
     * @param model куда мы будем складывать данные для пользователя
     * @return возвращает имя файла который хотим отобразить,
     * т.к. mapping не указываем то это означает, что запросы будут отправлены на туже страницу от куда пришло сообщение
     */
    @PostMapping("filter")// из поля name
    public String filter(@RequestParam String filter, Map<String, Object> model){
        System.out.println("filter ");
        mylogger.debug("log dsfdsfs ");
//        log.debug("if true ");

        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
//            log.debug("if true ");
            System.out.println("if true ");

            messages = messageRepo.findByTag(filter);
        }else {
            System.out.println("if else ");

            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}