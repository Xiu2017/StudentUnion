--use master
use StudentUnion
go
--====================��ӹ�����Ĳ�������===================
--��Ӳ��԰༶
insert into su_class values('����')
insert into su_class values('S130')
insert into su_class values('S131')
insert into su_class values('S132')
insert into su_class values('S133')
insert into su_class values('S134')
insert into su_class values('S135')
insert into su_class values('S136')
insert into su_class values('S137')
insert into su_class values('S138')
insert into su_class values('S139')
insert into su_class values('S140')
insert into su_class values('T130')
insert into su_class values('T131')
insert into su_class values('T132')
insert into su_class values('T133')
insert into su_class values('T134')
insert into su_class values('T135')
insert into su_class values('T136')
insert into su_class values('T137')
insert into su_class values('T138')
insert into su_class values('T139')
insert into su_class values('T140')
go
--������в��ż���ɫ
insert into su_dept values('����Ա')
insert into su_dept values('ѧԱ')
insert into su_dept values('���') --000002
insert into su_dept values('�ͼ첿')
insert into su_dept values('���鲿') --000004
insert into su_dept values('ѧϰ��') --000005
insert into su_dept values('������')
insert into su_dept values('���岿') --000007
insert into su_dept values('������') --000008
insert into su_dept values('���Ų�') --000009
insert into su_dept values('������') --000010
go
--���ѧ�������ֽ�ɫ
insert into su_student values('000000','admin','00000000',0,0)
insert into su_student values('000001','��һ',default,1,1)
insert into su_student values('000002','�ƶ�',default,2,2)
insert into su_student values('000003','����',default,3,3)
insert into su_student values('000004','����',default,4,4)
insert into su_student values('000005','����',default,5,5)
insert into su_student values('000006','����',default,6,6)
insert into su_student values('000007','Ǯ��',default,7,7)
insert into su_student values('000008','���',default,8,8)
insert into su_student values('000009','���',default,9,9)
insert into su_student values('000010','��ʮ',default,10,10)
insert into su_student values('000011','ѧԱ1',default,11,1)
insert into su_student values('000012','ѧԱ2',default,12,1)
insert into su_student values('000013','ѧԱ3',default,13,1)
insert into su_student values('000014','ѧԱ4',default,14,1)
insert into su_student values('000015','ѧԱ5',default,15,1)
insert into su_student values('000016','ѧԱ6',default,16,1)
insert into su_student values('000017','ѧԱ7',default,17,1)
go
--���5�����Թ���
insert into su_notice values('ϵͳ',0,default,default,'�������ڲ��ԵĹ���001','����һ��������Ϣ001','public')
insert into su_notice values('ϵͳ',0,default,default,'�������ڲ��ԵĹ���002','����һ��������Ϣ002','public')
insert into su_notice values('ϵͳ',0,default,default,'�������ڲ��ԵĹ���003','����һ��������Ϣ003','public')
insert into su_notice values('ϵͳ',0,default,default,'�������ڲ��ԵĹ���004','����һ��������Ϣ004','public')
insert into su_notice values('ϵͳ',0,default,default,'�������ڲ��ԵĹ���005','����һ��������Ϣ005','public')
go


--=====================���===================

--����������ڲ��Ե�����������Ϣ
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('a',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('a',@n+@i,@j,'',@a,'','',@m,'����',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%2=0 then '������-5' else '' end)
			set @m=(case when @d%2=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('b',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('b',@n+@i,@j,'',@a,'','',@m,'����',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%3=0 then '������-5' else '' end)
			set @m=(case when @d%3=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('c',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('c',@n+@i,@j,'',@a,'','',@m,'����',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%5=0 then '������-5' else '' end)
			set @m=(case when @d%5=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
--select * from su_dorminfo
--select * from su_dormregister
--select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where datediff(mm,d.ddate,'2017-6-1')=0 and d.dbuilding='a' and d.dnumber='106'
--����������ڲ��Ե�һ���µ�����������


--====================���鲿===================

insert into Property values('��״ֽ',40,0.5,20,GETDATE());
insert into Property values('�����Ʒ',10,5,50,GETDATE());
insert into Property values('ѧ����Ʒ',10,5,50,GETDATE());
insert into Property values('�װ��',2,5,10,GETDATE());
insert into Property values('��״ֽ2',40,0.5,20,GETDATE());
insert into Property values('�����Ʒ2',10,5,50,GETDATE());
insert into Property values('ѧ����Ʒ2',10,5,50,GETDATE());
insert into Property values('�װ��2',2,5,10,GETDATE());
insert into Property values('��״ֽ3',40,0.5,20,GETDATE());
insert into Property values('�����Ʒ3',10,5,50,GETDATE());
insert into Property values('ѧ����Ʒ3',10,5,50,GETDATE());
insert into Property values('�װ��3',2,5,10,GETDATE());

--====================���Ų�===================

insert into dp_clubDept values('������')
insert into dp_clubDept values('�鷨��')
insert into dp_clubDept values('������')
insert into dp_clubDept values('��ë����')
insert into dp_clubDept values('������')
insert into dp_clubDept values('ƹ������')

insert into dp_clubDept_attendance values(40,30,10,default,1)
insert into dp_clubDept_attendance values(50,30,20,default,2)
insert into dp_clubDept_attendance values(30,30,0,default,3)
insert into dp_clubDept_attendance values(40,30,10,default,4)
insert into dp_clubDept_attendance values(50,30,20,default,5)
insert into dp_clubDept_attendance values(30,30,0,default,6)

go
insert into br_type values('Ѱ������')
insert into br_type values('ʧ������')


insert into br_notice values('Lucy','Ѱ������1','2017.06.23',1)
insert into br_notice values('Lucy','Ѱ������2','2017.06.22',1)

insert into br_notice values('Lucy','ʧ������1','2017.06.23',2)
insert into br_notice values('Lucy','ʧ������2','2017.06.22',2)


insert into br_StuEssay values('С��','һ·���壬��������','�������֣�͸�Ų�ľ���뻨��������ల��....��','2017.06.22')
insert into br_StuEssay values('С��','�Կ�һ�������������','��������һ���¶������ã�....��','2017.06.22')
insert into br_StuEssay values('С��2','����','��������һ���¶������ã�....��','2017.06.22')
insert into br_StuEssay values('С��3','����','��������һ���¶������ã�....��','2017.06.22')
insert into br_StuEssay values('С��4','����','��������һ���¶������ã�....��','2017.06.22')


--====================������===================
---------ΪѰ��ʧ������30������------------
go
declare @i int
set @i=1
while(@i<21)
begin
insert into br_notice values('admin'+convert(varchar,@i*3/2),'Ѱ������'+convert(varchar,@i),getdate(),1)
insert into br_notice values('Jack'+convert(varchar,@i*3/2),'ʧ������'+convert(varchar,@i),getdate(),2)
	set @i=@i+1
end
go


--====================������===================

insert into sponsor values('ǧ���̵�','������Ȫˮ2000ƿ','2017-5-20','����','�ɹ�')
insert into sponsor values('��ͥ��Ե','��','2017-5-20','����','δ�ɹ�')
insert into sponsor values('��0-0','�ֽ�5000Ԫ','2017-5-20','����','�ɹ�')
insert into sponsor values('�E','��','2017-5-20','X','���ɹ�')
insert into sponsor values('������','����','2017-5-20','����','�ɹ�')


insert into Event values('У���˶���','���ó���,ά�ֳ���','������ȫ������','2017-4-20')
insert into Event values('��������','���ó���,ά�ֳ���','������ȫ��','2017-8-15')


insert into Lecture values('�˳�','���ִ�ʦ','2017-5-20')
insert into Lecture values('���','���ߴ�ʦ','2017-7-15')


insert into Propaganda values('У������','�������㲥','2017-3-20')
insert into Propaganda values('У������','���硢���','2017-5-15')
insert into Propaganda values('ѧУ��ʾ��','��ʾ','2017-5-20')



--====================���岿===================

insert into Stu_Sports values('�Ϸ�У��','�㶫�Ϸ�ITѧԺ����20�������','2017-7-7','����','����','����ۿ�','123456789',default)
insert into Stu_Sports values('�Ϸ�У��2','�㶫�Ϸ�ITѧԺ����20�������,��ȫУʦ��,����������ؽ�Ŀ׼��','2017-7-7','����','����','����ۿ�','123456789',default)
insert into Stu_Sports values('sss','ccc','2017-7-7','vv','vv','vvv','123456789',default)



--====================ѧϰ��===================


insert into lifeDep_book_state values('����')
insert into lifeDep_book_state values('���')
insert into lifeDep_book_state values('��')
insert into lifeDep_book_state values('��ʧ')


insert into Assistant values(2,3)
insert into Assistant values(4,8)
insert into Assistant values(6,9)
insert into Assistant values(3,2)
insert into Assistant values(6,7)

go
insert into lifeDep_book_info values('ax17230','�����뺣','������',1)
insert into lifeDep_book_info values('al00326','���¶�','�Ҹ�˹',1)
insert into lifeDep_book_info values('bw27003','��','����١�������',1)
insert into lifeDep_book_info values('ax62039','����������','���ա�������',1)
insert into lifeDep_book_info values('fs00268','�����ʷ','ʷ�ٷҡ�����',1)
insert into lifeDep_book_info values('xa02147','�Ƽ�����','������˹',1)
insert into lifeDep_book_info values('wg26130','��������','����',1)
insert into lifeDep_book_info values('bw00261','�߳�','�����',1)
insert into lifeDep_book_info values('lz25000','�ݷ���','������',1)
insert into lifeDep_book_info values('wg26140','��������','����',1)
insert into lifeDep_book_info values('bw00861','�߳�','�����',3)
insert into lifeDep_book_info values('lz25900','�ݷ���','������',4)


--select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where datediff(dd,d.ddate,'2017-6-30')=0 and d.dcid=1


----------------��ѯ��������------------
--use StudentUnion
--select * from su_class
--select * from su_dept
--select * from su_student
--select * from su_notice
--select * from su_dorminfo
--select * from su_dormregister
--drop table su_dormregister

-------------��ѯ����-------------

--���ϲ�ѯѧ����Ϣ��select s.*,c.cname,d.dname from su_class c,su_dept d left join su_student s on sno='000002' and spwd='gdnf2017' where s.scid = c.cid and s.sdid = d.did

--��ҳ���ϲ�ѯ��select n.*,d.dname from su_dept d left join (select row_number() over(order by ndate desc) rn,* from su_notice where ndid=4 or ndid=0 and npublisher='ϵͳ') n on n.rn>0 and n.rn<=3 where n.ndid = d.did

--�������ڲ�ѯ��set language N'Simplified Chinese' select datename(weekday, getdate())

--���ڱ�Ų�ѯ��select datepart(weekday, getdate())

--��ʽ�����ڣ�Select CONVERT(varchar(100), GETDATE(), 20)

--���ϲ�ѯ������Ϣ��select d.*,c.cname from su_dorminfo d left join su_class c on d.dcid=c.cid where dbuilding='a' and dnumber like '1%'

--���ϲ�ѯ�������ҵǼ���Ϣ��select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where d.dnumber like '1%' and datediff(dd,d.ddate,getdate())=0

--��ҳ��ѯ���ҵ�����Ϣ��select d.*,c.cname from su_class c left join (select row_number() over(order by ddate) rn,* from su_dormregister where datediff(mm,ddate,'2017-06-01')=0 and dbuilding='a' and dnumber='101' and dcid='1') d on d.rn>0 and d.rn<=12  where dcid=c.cid

--��ѯ���ҵ�����Ϣ��������select COUNT(*) from su_dormregister where datediff(mm,ddate,'2017-07-01')=0 and dbuilding='a' and dnumber='101' and dcid='1'