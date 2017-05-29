CREATE TABLE `csdnblog` (
   `id` varchar(50) NOT NULL COMMENT 'id',
   `title` varchar(200) NOT NULL default '' COMMENT 'title',
   `date` varchar(50) NOT NULL default '' COMMENT 'date',
   `tags` varchar(50) NOT NULL default '' COMMENT 'tags',
   `category` varchar(50) NOT NULL default '' COMMENT 'category',
   `view` varchar(50) NOT NULL default '' COMMENT 'view',
   `comments` varchar(50) NOT NULL default '' COMMENT 'comments',
   `copyright` varchar(50) NOT NULL default '' COMMENT 'copyright',
   PRIMARY KEY  (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='`csdnblog`'