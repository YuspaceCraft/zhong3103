package com.qf.config;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpController<M extends BaseMapper<B>, B> {
	public static void printj(Object obj) {
		try {
			System.out.println(obj instanceof String ? (String) obj : new ObjectMapper().writeValueAsString(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Autowired
	public M mapper;
	
	public String fmtError(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String[] info = sw.toString().split("Duplicate entry");
			return info.length <= 1 ? "" :
			info[1].split("for key")[0].replace("'","").trim()+"已经有了";
		} catch (Exception exception) {
			return "";
		} finally {
			if (pw != null) pw.close();
		}
	}
	
	@GetMapping("/get")
	public List<B> get() {
		return mapper.selectList(null);
	}
	
	@GetMapping
	public List<B> select() {
		QueryWrapper<B> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("id");
		return mapper.selectList(wrapper);
	}
	
	@GetMapping("{id}")
	public List<B> delete(@PathVariable String id) {
		if (id != null && id.contains("page")) {
			QueryWrapper<B> wrapper = new QueryWrapper<>();
			wrapper.orderByDesc("id");
			int page = Integer.valueOf(id.replace("page",""));
			return mapper.selectPage(new Page<>(page,10),wrapper).getRecords();
		} else {
			mapper.deleteById(id);
			return null;
		}
	}
	
	@GetMapping("/count")
	public int count() {
		return mapper.selectCount(null);
	}
	
	@PostMapping("{id}")
	public Object update(@RequestBody B bean) {
		String alert = NotNullUtil.isBlankAlert(bean);
		if (alert != null)
			return alert.contains("请填写") ? "请完善信息！" : alert;
		try {
			return mapper.updateById(bean);
		} catch (Exception e) {
		if (e instanceof DuplicateKeyException) {
			return fmtError(e);
		} else {
			e.printStackTrace();
			return "错误----500";
		}}
	}
	
	@PostMapping
	public Object insert(@RequestBody B bean) {
		String alert = NotNullUtil.isBlankAlert(bean);
		if (alert != null)
			return alert.contains("请填写") ? "请完善信息！" : alert;
		try {
			return mapper.insert(bean);
		} catch (Exception e) {
		if (e instanceof DuplicateKeyException) {
			return fmtError(e);
		} else {
			e.printStackTrace();
			return "错误----500";
		}}
	}
	
	@RequestMapping("/bean/{id}")
	public B bean(@PathVariable int id) {
		return mapper.selectById(id);
	}
	
	@RequestMapping("/bean")
	public List<B> beanList(int id) {
		List<B> list = new ArrayList<>();
		list.add(bean(id));
		return list;
	}
	
	@RequestMapping("/file")
	public String elFile(MultipartFile file, String column) {
		String fileName = file.getOriginalFilename();
		FileUtil.createFile("D:/JavaWorkspace/commodity");
		try {
			file.transferTo(new File("D:/JavaWorkspace/commodity/" + fileName));
		} catch (Exception e) { }
		String url = "/commodity/" + fileName;
		if (column != null && !"".equals(column.trim())) {
			url += "?column=" + column;
		}
		return url;
	}
}
