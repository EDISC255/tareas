# TAREAS
## se creara la base de datos tareas
### para mysql
```sql
create database tareas;
 con la tabla
create table tarea(
   id int(8) auto_increment,
   titulo varchar(100),
   descripcion mediumtext,
   fecha date,
   status int(1),
   primary key(id)
)engine=innobd;
```

### para sqlite
```
sqlite tareas.db
```
``` sql
create table tarea(
	id integer primary key autoincrement,
	titulo text,
	descripcion text,
	fecha text,
	status integer
)
```

