package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.entity.Type;
import com.southwind.repository.MenuRepository;
import com.southwind.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    //不会自动创建repository，需要通过扫描的方式让ioc扫描进来，需要在启动类添加一些MapperScan注解
    // mybatis创建动态代理对象，容器里拥有这个对象，否则repository是空指针
    @Autowired
    private MenuRepository menuRepository;
    //给findTypes方法使用
    @Autowired
    private TypeRepository typeRepository;
    @GetMapping("/index")
    public String index(){
        return "menu的端口 "+ this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    // 返回layui框架标准格式，用MenuV0包装类返回，根据layui标准格式构造MenuV0包装类
    public MenuVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        List<Menu> list=  menuRepository.findAll(index,limit);
        return new MenuVO(0,"",menuRepository.count(),list);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }


    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findAll();
    }
    // json数据从client传过来，需要带着requestBody，要不然没法读取到，需要把json映射成java对象
    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menuRepository.findById(id);
    }
    // json数据从client传过来，需要带着requestBody，要不然没法读取到，需要把json映射成java对象,与save同理
    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }

}
