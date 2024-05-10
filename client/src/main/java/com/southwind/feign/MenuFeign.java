package com.southwind.feign;

import com.southwind.entity.Menu;
import com.southwind.entity.MenuVO;
import com.southwind.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//关联到服务提供者
@FeignClient(value = "menu")
//相当于只到了8011端口，还没到服务
public interface MenuFeign {
    //只有在列表展示的时候，为了配合layui的格式，才用到格式化的menuV0即，内部加上code，msg。其他时候都用menu
    @GetMapping("/menu/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);
    @DeleteMapping("/menu/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);
    @GetMapping("/menu/findById/{id}")
    public Menu findById(@PathVariable("id") long id);

    @GetMapping("/menu/findTypes")
    public List<Type> findTypes();

    //通过feign调用menu微服务
    @PostMapping("/menu/save")
    public void save(Menu menu);

    @PutMapping("/menu/update")
    public void update(Menu menu);
}
