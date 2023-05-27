package com.springrest.springrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDao courseDao;

	public CourseServiceImpl() {
		
	}
	
	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
		Optional<Course> courseOptional = courseDao.findById(courseId);
		return courseOptional.orElse(null);
	}

	@Override
	public Course addCourse(Course course) {
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Long courseId, Course updatedCourse) {
		courseDao.save(updatedCourse);
		return updatedCourse;
	}

	@Override
	public boolean deleteCourse(Long courseId) {
		Optional<Course> courseOptional = courseDao.findById(courseId);
		if(courseOptional.isPresent()) {
			Course course = courseOptional.get();
			courseDao.delete(course);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Course patchCourse(Long courseId, Course patchCourse) {
		Optional<Course> courseOptional = courseDao.findById(courseId);
		if(courseOptional.isPresent()) {
			Course course = courseOptional.get();
			if(patchCourse.getDescription()!=null) {
				course.setDescription(patchCourse.getDescription());
			}
			Course updatedCourse = courseDao.save(course);
			return updatedCourse;
		}
		else {
			return null;
		}
	}
}
