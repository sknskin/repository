use employees;

show tables;

desc departments;

select * from departments;

select * from dept_emp;

SELECT d.dept_name, e.first_name, e.last_name
FROM employees e, dept_emp de, departments d
WHERE e.emp_no = de.emp_no and d.dept_no = de.dept_no and 
	  de.to_date = '9999-01-01' and 
      d.dept_no = 'd005'
ORDER BY e.first_name;

SELECT e.first_name, e.last_name, de.from_date, de.to_date, d.dept_name
FROM employees e, dept_emp de, departments d
WHERE e.emp_no = de.emp_no and d.dept_no = de.dept_no and e.emp_no = 100001
order by de.from_date;

SELECT e.emp_no, COUNT(*) 근무부서수
FROM employees e, dept_emp de, departments d
WHERE e.emp_no = de.emp_no and d.dept_no = de.dept_no
GROUP BY e.emp_no
HAVING count(*) > 1;

select * from dept_manager;

select e.*
FROM employees e, dept_manager dm, departments d
WHERE e.emp_no = dm.emp_no and d.dept_no = dm.dept_no and 
           dm.to_date = '9999-01-01' and 
           d.dept_no = 'd007';
           
commit;



