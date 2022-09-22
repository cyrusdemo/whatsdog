create table restaurant(
    [id]        int not null primary key identity(1,1)
    [name]      varchar(255) not null,
    [address]   varchar(255) not null,
    [phone]     varchar(255) null,
    [website]   varchar(255) null
);
go

create procedure GetRestaurants
    @name nvarchar(50) = '',   
    @addr nvarchar(50) = '' 
as   
    set nocount on;  

    select
        id, [name], [address], [phone], [website] 
    from restaurant
    where (@name = '' or [name] like '%' + @name + '%')
      and (@addr = '' or [address] like '%' + @addr + '%');
go  