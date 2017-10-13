use master
go
if exists(select * from sysdatabases where name='StudentUnion')
	drop database StudentUnion
go
create database StudentUnion
go
use StudentUnion
go
--==========================����������==============================
----------------�༶��----------------
create table su_class(
	cid int primary key identity(0,1), --�༶id
	cname varchar(50) unique not null  --�༶����
)
go
----------------���ű�----------------
create table su_dept(
	did int primary key identity(0,1),  --����id
	dname varchar(50) not null  --��������
)
go
---------------ѧ����-------------
create table su_student(
	[sid] int primary key identity(1,1),  --ѧ��id
	sno varchar(50) unique not null,  --ѧ��
	sname varchar(50) not null,  --����
	spwd varchar(50) default('gdnf2017'),  --����
	scid int foreign key references su_class(cid) not null,  --�����༶
	sdid int foreign key references su_dept(did) not null  --��������
)
go
-------------�����--------------
create table su_notice(
	nid int primary key identity(1,1),  --����id
	npublisher varchar(20) not null,  --������
	ndid int foreign key references su_dept(did) not null,  --����id
	ndate datetime default(CONVERT(varchar(100), GETDATE(), 20)) not null,  --����ʱ��
	nweek varchar(10) default(DATEPART(weekday, GETDATE())) not null,  --����
	ntitle varchar(100) not null,  --����
	ncontent varchar(8000) not null,  --����
	nregion varchar(10) not null  --��������
)

go

--==========================���==============================

----------------������Ϣ----------------
create table su_dorminfo(
	did int primary key identity(1,1),  --���
	dbuilding varchar(10) not null,  --����
	dnumber varchar(20) not null,  --�����
	dcid int foreign key references su_class(cid) not null,  --�����༶
)
go
----------------���ҵǼ�----------------
create table su_dormregister(
	did int primary key identity(1,1),  --a��id
	dbuilding varchar(10) not null,  --����
	dnumber varchar(50) not null,  --�����
	dcid int foreign key references su_class(cid) not null,  --�����༶
	dstandarda varchar(200),  --���ֱ�׼a
	dstandardb varchar(200),  --���ֱ�׼b
	dstandardc varchar(200),  --���ֱ�׼c
	dstandardd varchar(200),  --���ֱ�׼d
	dmark varchar(50),  --����
	dliable varchar(50),  --������
	ddate datetime default(CONVERT(varchar(100), GETDATE(), 23))  --����
)
--==========================���鲿==============================

create table Department_check(
	cid int IDENTITY(1,1) NOT NULL,
	dname varchar(50) NOT NULL,
	allnum int NOT NULL,
	num int NOT NULL,
	lacknum int NOT NULL,
	ntime varchar(50) NOT NULL
)

create table Property( --������Ʊ�
	id int primary key identity(1,1),--������
	Goods varchar(200) not null, --��Ʒ����
	Num int not null, --��Ʒ����
	Price float not null, --��Ʒ�۸�
	TPrice float not null, --��Ʒ�ܼ�
	BuyTime datetime not null   --��ǰ����ʱ��
)


--==========================���Ų�==============================


--�������Ų��������ŵı�
create table dp_clubDept
(
	clubid int primary key identity(1,1),
	clubname varchar(20),--��������
)
--���ŵĿ��ڱ�
create table dp_clubDept_attendance
(
	attid int primary key identity(1,1),
	peoplesnum int ,--������
	peoplecomenum int,--ʵ������
	peoplenocome int,--ȱ������
	thetime datetime default(getdate()),
	aclubid int foreign key references dp_clubDept(clubid) not null--��������
)


--==========================������==============================

--------------���ͱ�---------------------
create table br_type(
	tid int identity(1,1) primary key,
	tname varchar(50) not null
)

----------------Ѱ��ʧ���--------------------
go
create table br_notice(
	nid int primary key identity(1,1),  --���id
	nname varchar(50) not null,  --�û���
	ncontent varchar(400) not null,  --����
	ntime datetime,  --������ǰʱ��
	ntid int foreign key references br_type(tid)
)
------------ͬѧ�����-----------------
go
create table br_StuEssay(
	eid int primary key identity(1,1),  --���id
	ename varchar(50) not null,  --�û���
	etitle varchar(50) not null,  --����
	econtent varchar(4000) not null,  --����
	etime datetime  --������ǰʱ��
)


--==========================������==============================
/*��Ա��������*/ 
/*������Ϣ��*/
create table sponsor(
sid int primary key identity(1,1),
sevent varchar(50) not null,
things varchar(500) not null,
datatime  datetime,
sname varchar(50) not null,
result varchar(10)
)
/*���Ϣ*/
create table Event(
eid int primary key identity(1,1),
event varchar(50) not null,
ethings varchar(500) not null,
ename varchar(50) not null,
datatime  datetime
)
/*������Ϣ*/
create table Lecture(
lid int primary key identity(1,1),
pname varchar(50) not null,
lname varchar(30) not null,
ldatatime datetime
)
/*������Ϣ*/
create table Propaganda(
pid int primary key identity(1,1),
pname varchar(50) not null,
content varchar(100) not null,
pdatatime datetime
)


--==========================���岿==============================

create table Stu_Sports(
	id int primary key identity(1,1),
	stitle varchar(200) not null, --����
	sncontent varchar(1000) not null, --����
	stime datetime not null, --ʱ��
	sleader varchar(100) not null, --������
	shelp varchar(100), --Э������
	scontact varchar(100) not null, --��ϵ��ʽ
	sremarks varchar(200), --��ע
	stest int not null default(0)
)


--==========================ѧϰ��==============================

--================================================��һ��ִ��=================================================
use StudentUnion
go
--================================================�ڶ���ִ��=================================================
--�鼮״̬����2
create table lifeDep_book_state
(
	l_b_s_id int primary key identity,
	l_b_s_state varchar(5) not null
)
go
--�鼮��Ϣ��,��1
create table lifeDep_book_info
(
	l_b_id int primary key identity,
	--��ţ����֣�����
	l_b_no varchar(50) unique not null,
	l_b_name varchar(999) not null,
	l_b_author varchar(999) not null,
	--�鼮״̬��Ϣ���ɽ裬������𻵣���ʧ��
	l_b_state int default(1) foreign key references lifeDep_book_state(l_b_s_id) not null
)
go
--�鼮״̬��Ϣ����3
create table lifeDep_book_state_info
(
	l_b_s_i_id int primary key identity,
	l_b_s_i_bookno int foreign key references lifeDep_book_info(l_b_id) not null,--�����鼮���
	l_b_s_i_stuno int foreign key references su_student([sid]) not null,--������ѧ��
	l_b_s_i_outtime datetime default(GETDATE()),--���ʱ��
	l_b_s_i_intime datetime default(null),--�黹ʱ��
	l_b_s_i_state int foreign key references lifeDep_book_state(l_b_s_id) default(2)--��ǰ״̬
)
go
create table Assistant	--�������̱�
(
	aid int primary key identity,
	asno int foreign key references su_student([sid]) not null,--����ѧ��
	acno int foreign key references su_class(cid) not null --���̵̽İ༶
)

--=============================================������ִ�У�һֱ�����====================================================
go
--���鴥����
--���ı�1���鼮״̬��������3�е��鼮״̬��Ϊ�ѹ黹�������Ĺ黹ʱ��Ϊ��ǰʱ��
create trigger book_state_update on lifeDep_book_info
after update
as
	if(select l_b_state from inserted) = 1
	begin
		update a 
		set a.l_b_s_i_state = b.l_b_state, 
			a.l_b_s_i_intime = GETDATE() 
		from lifeDep_book_state_info a,inserted b 
		where a.l_b_s_i_bookno = b.l_b_id 
			and a.l_b_s_i_id = 
				(select top 1 l_b_s_i_id from lifeDep_book_state_info order by l_b_s_i_id desc)
end
go
--���鴥����
--������ڱ�3����һ����¼����־����ͬʱ�ı��1���鼮״̬Ϊ2�������
create trigger book_state_loan on lifeDep_book_state_info
after insert
as
	update a 
	set l_b_state = b.l_b_s_i_state 
	from lifeDep_book_info a, inserted b 
	where a.l_b_id = b.l_b_s_i_bookno
go
--��ҳ�洢����
go
create proc usp_pag
 @bookno varchar(50),
 @bookname varchar(999),
 @pageIndex int,   --��ǰҳ��
 @pageSize int,   --ÿҳ������
 @pageCount int output  --����  �ܹ�����ҳ
as
 declare @count int --�ܹ�������
 select @count =COUNT(*) from (select a.l_b_id, a.l_b_no, a.l_b_name, a.l_b_author, b.l_b_s_state from lifeDep_book_info a join lifeDep_book_state b on a.l_b_state=b.l_b_s_id where a.l_b_no like '%'+@bookno+'%' or a.l_b_name like '%'+@bookname+'%') as a
 set @pageCount = CEILING( @count*1.0/@pageSize)
 select *,@pageCount as ys from
(select *,ROW_NUMBER() over(order by l_b_id) as num
from (select a.l_b_id, a.l_b_no, a.l_b_name, a.l_b_author, b.l_b_s_state from lifeDep_book_info a join lifeDep_book_state b on a.l_b_state=b.l_b_s_id where a.l_b_no like '%'+@bookno+'%' or a.l_b_name like '%'+@bookname+'%')as b) as t
where num between @pageSize*(@pageIndex-1) + 1 and @pageSize*@pageIndex
--���̲�ѯ�洢����
go
create proc usp_ass
 @str varchar(50),
 @pageIndex int,   --��ǰҳ��
 @pageSize int,   --ÿҳ������
 @pageCount int output  --����  �ܹ�����ҳ
as
 declare @count int
 select @count=COUNT(*) from 
 (select t.* from (
  select a.aid, s.sno, s.sname, 
   (select top 1 cname  
   from su_student s2, su_class c2 
   where s2.scid = c2.cid and s2.[sid]=s.[sid]) owncname, --���ڰ༶
   c.cname --���̰༶
  from Assistant a, su_student s, su_class c 
  where a.asno = s.[sid]  and a.acno = c.cid 
 )t
 where owncname like '%'+@str+'%' 
  or t.sno = '%'+@str+'%' 
  or t.sname like '%'+@str+'%' 
  or t.cname like '%'+@str+'%')a
  set @pageCount = CEILING( @count*1.0/@pageSize)
 select *,@pageCount as ys from
(select *,ROW_NUMBER() over(order by sno) as num
from (
select t.* from (
  select a.aid, s.sno, s.sname, 
   (select top 1 cname  
   from su_student s2, su_class c2 
   where s2.scid = c2.cid and s2.[sid]=s.[sid]) owncname, --���ڰ༶
   c.cname --���̰༶
  from Assistant a, su_student s, su_class c 
  where a.asno = s.[sid]  and a.acno = c.cid 
 )t
 where owncname is not null and (owncname like '%'+@str+'%'
  or t.sno like '%'+@str+'%' 
  or t.sname like '%'+@str+'%' 
  or t.cname like '%'+@str+'%')
) as t5)t4
where num between @pageSize*(@pageIndex-1) + 1 and @pageSize*@pageIndex

go

create proc usp_log
 @bno varchar(50), --�鼮���
 @pageIndex int, --��ǰҳ��
 @pageSize int, --ÿҳ������
 @pageCount int output --���� �ܹ�����ҳ
as
 declare @count int  --������
 select @count = count(*) from (select a.l_b_s_i_id, b.l_b_no, b.l_b_name, c.sno, c.sname, a.l_b_s_i_outtime, a.l_b_s_i_intime, d.l_b_s_state from lifeDep_book_state_info a, lifeDep_book_info b, su_student c, lifeDep_book_state d where a.l_b_s_i_bookno = b.l_b_id and a.l_b_s_i_stuno = c.[sid] and a.l_b_s_i_state = d.l_b_s_id and b.l_b_no = @bno) as t1 
 set @pageCount = CEILING( @count*1.0/@pageSize) 
 select *, @pageCount as ys from 
 (select *, ROW_NUMBER() over(order by l_b_s_i_id desc) as num from
 (select a.l_b_s_i_id, b.l_b_no, b.l_b_name, c.sno, c.sname, a.l_b_s_i_outtime, a.l_b_s_i_intime, d.l_b_s_state from lifeDep_book_state_info a, lifeDep_book_info b, su_student c, lifeDep_book_state d where a.l_b_s_i_bookno = b.l_b_id and a.l_b_s_i_stuno = c.[sid] and a.l_b_s_i_state = d.l_b_s_id and b.l_b_no = @bno) as t2) as t3  
 where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
 
 
 --=====================================����Ա=====================================
 
--��ѯ�༶��ҳ������Ա��
go
create proc adm_claInfo
 @pageIndex int,
 @pageSize int,
 @pageCount int output
as
 declare @count int
 select @count = count(*) from (select cid, cname , count(scid) as l from (select c.cid, c.cname, s.scid from su_class c left join su_student s on c.cid = s.scid) as t1 group by cid, cname)as t2
 set @pageCount = CEILING( @count*1.0/@pageSize) 
   select *, @pageCount as ys from 
   (select *, ROW_NUMBER() over(order by cid) as num from
	(select cid, cname , count(scid) as l from (select c.cid, c.cname, s.scid from su_class c left join su_student s on c.cid = s.scid) as t1 group by cid, cname)t3)t4
  where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
go

--ѧ����Ϣ��ѯ������Ա��
create proc adm_stuInfo
 @sno varchar(50), 
 @sclass varchar(50), 
 @sdep varchar(50), 
 @pageIndex int,   --��ǰҳ��
 @pageSize int,   --ÿҳ������
 @pageCount int output  --����  �ܹ�����ҳ
as
 declare @count int  --������
 select @count = count(*) from (select s.sno, s.sname, s.spwd, c.cname, d.dname from su_student s, su_class c, su_dept d where s.scid = c.cid and s.sdid = d.did and ((s.sno like '%'+@sno+'%' or s.sname like '%'+@sno+'%') and c.cname like '%'+@sclass+'%' and d.dname like '%'+@sdep+'%') and s.sno != '000000' ) as t1
 set @pageCount = CEILING( @count*1.0/@pageSize) 
  select *, @pageCount as ys from 
   (select *, ROW_NUMBER() over(order by sno) as num from
	(select s.sno, s.sname, s.spwd, c.cname, d.dname from su_student s, su_class c, su_dept d where s.scid = c.cid and s.sdid = d.did and ((s.sno like '%'+@sno+'%' or s.sname like '%'+@sno+'%') and c.cname like '%'+@sclass+'%' and d.dname like '%'+@sdep+'%') and s.sno != '000000' )t2)t3
  where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
go


--=========================�ͼ첿===============================
--drop table su_inspection
create table su_inspection(
	iid int primary key identity(1,1),
	icid int foreign key references su_class(cid) not null,  --�༶id
	iall int not null,  --Ӧ������
	ione int,  --һ��ʵ��
	itwo int,  --����ʵ��
	istandarda int,  --����
	istandardb int,  --���
	istandardc int,  --ȱ��
	istandardd int,  --Υ��
	istandarde int,  --���������۷�
	imark int,  --�ܿ۷�
	iclaperson varchar(50),  --�༶������
	iperson varchar(50),  --������
	idate datetime default(GETDATE())  --����
)