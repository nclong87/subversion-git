/*
SQLyog Enterprise - MySQL GUI v6.13
MySQL - 5.1.41-community-log : Database - saigonmoi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`create_time`,`enabled`,`deleted`) values (1,'2012-03-25 16:32:14',1,0),(2,'2012-05-25 16:32:14',1,0),(3,'2012-05-25 16:32:14',1,1),(16,'2012-05-20 16:31:48',1,0),(15,'2012-05-19 02:08:36',1,0),(14,'2012-05-19 00:22:44',1,1),(13,'2012-05-19 00:21:29',1,1),(17,'2012-05-20 17:26:54',0,1);

/*Table structure for table `article_en` */

DROP TABLE IF EXISTS `article_en`;

CREATE TABLE `article_en` (
  `article_id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `article_en` */

insert  into `article_en`(`article_id`,`title`,`content`) values (1,'Seafood restaurant opened New Saigon','Seafood restaurant opened New Saigon'),(2,'Promotion sixth','Promotion sixth'),(9,NULL,NULL),(10,NULL,NULL),(13,'Test 2','test 2<br>'),(14,'Test 3','test 3<br>'),(15,'Ngoc Trinh ceiling by another shock situation','<span id=\"result_box\" class=\"\" lang=\"en\"><span class=\"\" title=\"Xuất hiện trong một sự kiện vào tối qua (17/5), chân dài Ngọc Trinh khá tự tin, dường như cô bỏ mặc scandal với phát ngôn &quot;Tôi giỏi nhất là ngoan&quot;.\">Appear\r\n in an event last night (17/5), long-legged pretty confident Ngoc Trinh,\r\n apparently left her with speech scandal \"is good I\'m good.\" </span><span class=\"\" title=\"Không hiểu vô tình hay cố ý nhưng những ngôi sao nổi tiếng có những phát ngôn gây sốc như Đàm Vĩnh Hưng, Phi Thanh Vân, Thuỵ Anh, Lý Nhã Kỳ...đều có mặt.\">Do\r\n not understand accidental or intentional, but the celebrities have such\r\n shocking statements Dam Vinh Hung, Phi Thanh Van, Thuy Anh, Ly Nha Ky \r\n... were available.<br></span></span><div style=\"text-align: center;\"><span id=\"result_box\" class=\"\" lang=\"en\"><span class=\"\" title=\"Không hiểu vô tình hay cố ý nhưng những ngôi sao nổi tiếng có những phát ngôn gây sốc như Đàm Vĩnh Hưng, Phi Thanh Vân, Thuỵ Anh, Lý Nhã Kỳ...đều có mặt.\"><img alt=\"\" src=\"http://localhost/saigonmoi/web/upload/post/824191281087722.jpg\" align=\"none\"></span></span><br><span id=\"result_box\" class=\"\" lang=\"en\"><span class=\"\" title=\"Không hiểu vô tình hay cố ý nhưng những ngôi sao nổi tiếng có những phát ngôn gây sốc như Đàm Vĩnh Hưng, Phi Thanh Vân, Thuỵ Anh, Lý Nhã Kỳ...đều có mặt.\"></span></span></div><span id=\"result_box\" class=\"\" lang=\"en\"><span class=\"\" title=\"Khi MC Thanh Bạch hỏi hẳng đến scandal sau bài phỏng vấn gần đây của Ngọc Trinh gây nhiều ý kiến trái chiều.\">When MC Thanh Bach inquiries to scandal after a recent interview, Ngoc Trinh caused many mixed opinions. </span><span class=\"\" title=\"Cô không ngần ngại bộc bạch những suy nghĩ của mình.\">She does not hesitate to reveal his thoughts. </span><span class=\"\" title=\"Ngọc Trinh cho biết: &quot;Thực sự em sẽ không bao giờ hối tiếc về những gì mình nói vì đó là đúng sự thật đối với em, cũng như chị Phi Thanh Vân có nói rằng nếu nói dối thì sợ một ngày nào đó người ta\">Ngoc\r\n Trinh said: \"Actually I will never regret what I said because it\'s true\r\n for me, as she Phi Thanh Van has said that if a lie is afraid that one \r\nday people </span><span class=\"\" title=\"sẽ \'khui\' mình ra, em rất sợ điều đó, và em chỉ còn biết là nói thật thôi ạ&quot;.\">will be \'opened\' her out, I am very afraid of that, and I could only say it is only a \".<br><br></span><span class=\"\" title=\"Ngọc Trinh chia sẻ thêm: “Em nghĩ những ý kiến mà anh nói chỉ là những ý kiến cá nhân thôi chứ không phải là ý kiến chung của tất cả các cô gái khác.\">More\r\n share Ngoc Trinh: \"I think the comments that he said is just personal \r\nopinion, not only the general opinion of all the other girls. </span><span class=\"\" title=\"Tại sao những người đó không nghĩ rằng tại sao xã hội lại tôn vinh em?\">Why do these people do not think why I honor society? </span><span class=\"\" title=\"Nếu xã hội tôn vinh em thì chắc chắn em có một thế mạnh nào đó&quot;.\">If you honor society then surely they have a certain strength. \"<br><br></span><span class=\"\" title=\"Nhiều người khen bản chất thật thà của Ngọc Trinh những cũng không ít người dự đoán, với sự thật thà này của cô, sẽ có một cơn mưa đá tiếp theo dội xuống đầu cô\">Many\r\n people would rather praise the true nature of Ngoc Trinh are also many \r\npeople predict, this honest with her, there will be a rain of stones \r\nnext to her head violently<br><br></span><span class=\"\" title=\"Tiếp đến, MC Thanh Bạch lại hỏi Ngọc Trinh câu thứ 2, &quot;Bây giờ mỗi lần Google thì không biết bao nhiều bài mà nói về Ngọc Trinh, đếm không xuể, có khá nhiều dư luận trái chiều và nói bạn &quot;ngoan&quot; như\">Next,\r\n MC Thanh Bach Ngoc Trinh asked the 2nd question, \"Now every time Google\r\n does not know how many articles that talk about Ngoc Trinh, \r\nNEVER-ENDING count, there are many conflicting opinions and tell you\" \r\ngood \"as </span><span class=\"\" title=\"thế thì người ta không chấp nhận, Ngọc Trinh nghĩ sao?&quot;.\">then we do not accept, Ngoc Trinh think? \".<br><br></span><span class=\"\" title=\"Ngọc Trinh thật thà cho biết: &quot;Ngay từ nhỏ em đã được ba dạy là phải sống thật thà, và khi lớn lên em cũng thấy rằng sự thật thà rất cần thiết trong cuộc sống. Và em cũng thấy là người ta thường thương những\">Honest\r\n Ngoc Trinh said: \"From three small children were taught to live \r\nhonestly, and growing up I also found that honesty is essential in life.\r\n And I also see is that people often trade </span><span class=\"\" title=\"người thật thà hơn là những người ít thật thà&quot;.\">honest people who are less than honest. \"<br><br></span><span class=\"\" title=\"Nhiều khán giả có mặt tại sự kiện đều khá hài lòng với phần trả lời thẳng thắn và chân thật của Ngọc Trinh.\">Many audience present at the event were quite satisfied with the responses of frank and honest Ngoc Trinh. </span><span class=\"\" title=\"Nhưng không ít người cũng dự đoán sẽ có một &quot;cơn mưa đá&quot; tiếp theo sẽ đổ lên đầu cô gái trẻ này với phần chia sẻ có đôi phần nhạy cảm.\">But\r\n many people also expected to have a \"rain stones\" The next would be \r\nheaped on this young woman to share a somewhat sensitive.</span></span>'),(16,'Chelsea win C1','<span style=\"background-color: rgb(0, 255, 204);\">llkk</span><br><img src=\"http://localhost/saigonmoi/web/upload/post/206332783781376.jpg\" width=\"345\"><br>lllk<br>'),(17,'test','test<br><img src=\"http://localhost/saigonmoi/web/upload/post/402759610648036.jpg\" width=\"687\"><br>');

/*Table structure for table `article_vi` */

DROP TABLE IF EXISTS `article_vi`;

CREATE TABLE `article_vi` (
  `article_id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `article_vi` */

insert  into `article_vi`(`article_id`,`title`,`content`) values (1,'Khai trương nhà hàng hải sản Sài Gòn Mới','<span>Ẩm thực Sài Gòn Mới mới khai trương từ ngày 8/10/2010 tại địa chỉ 38/4 Nguyễn Văn Linh, Quận 7, gần khu chế xuất Tân Thuận và khu đô thị mới Phú Mỹ Hưng. Nhà hàng nằm trong khuôn viên rộng khoảng 2.000m2. Nhà hàng được đầu tư bài bản, bố cục của quán khá hợp lý và bắt mắt.</span><br/>\r\n<p>\r\nNhà hàng thu hút khách hàng ngay từ phía ngoài với cổng được thiết kế dạng mở, hai cột to uy nghi,ngay phía trong là bồn cây và hoa với màu xanh của lá, màu đỏ của hoa như đón chào quan khách đến với nhà hàng. Bàn ghế của nhà hàng hoàn toàn bằng gỗ tạo sự gần gũi với thiên nhiên. Khu vực sân vườn chia làm 2 khu vực: một khu vực có mái che kiên cố và một khu vực có mái che di động để buổi tối khách hàng có thể ngồi ngoài trời thưởng thức buổi tiệc.\r\n</p>\r\n<p>\r\nMột yếu tố quan trọng tạo sự thành công cho quán và đảm bảo về chất lượng món ăn tại đây đó là nhà hàng đã tìm được đầu bếp giỏi, giàu kinh nghiệm, có tâm trong nghề để cùng phát triển công việc kinh doanh của nhà hàng.\r\n</p>\r\nMột đội ngũ nhân viên phục vụ nhiều kinh nghiệm, tận tình chu đáo, được đào tạo bài bản, một đội ngũ đầu bếp chuyên nghiệp giàu kinh nghiệm, sáng tạo, giỏi và một quản lý nhà hàng năng nổ, luôn tìm cái mới sẽ đem đến chất lượng dịch vụ tốt nhất phục vụ quý thực khách của mình.\r\n<p>\r\nMỗi khi các bạn nghĩ đến các cuộc nhậu hay đãi tiệc bạn bè, gia đình và người thân, hãy đừng quên lưu ý đến vấn đề vệ sinh an toàn thực phẩm nhé. Quý vị sẽ an tâm về vấn đề an toàn thực phẩm khi đến với nhà hàng chúng tôi bởi tất cả các nguyên liệu dùng cho chế biến món ăn đều được chúng tôi kiểm duyệt kỹ và có nguồn gốc rõ ràng.\r\n</p>'),(2,'Khuyến mãi 1/6','Khuyến mãi 1/6'),(3,'Test vi','test vi'),(9,NULL,NULL),(10,NULL,NULL),(13,'Test 2','test 2<br>'),(14,'Test 3','<span style=\"font-style: italic;\">tedst </span>3 abbc a<br>'),(15,'Ngọc Trinh trần tình bằng một cú sốc khác','<div style=\"text-align: center;\"><img src=\"http://localhost/saigonmoi/web/upload/post/824191281087722.jpg\" width=\"480\"><br></div><p>Xuất hiện trong một sự kiện vào tối qua (17/5), chân dài Ngọc Trinh khá tự tin, dường như cô bỏ mặc scandal với phát ngôn \"<em>Tôi giỏi nhất là ngoan</em>\".\r\n Không hiểu vô tình hay cố ý nhưng những ngôi sao nổi tiếng có những \r\nphát ngôn gây sốc như Đàm Vĩnh Hưng, Phi Thanh Vân, Thuỵ Anh, Lý Nhã \r\nKỳ...đều có mặt.&nbsp;</p>\r\n<p>\r\nKhi MC Thanh Bạch hỏi hẳng đến scandal sau bài phỏng vấn gần đây của \r\nNgọc Trinh gây nhiều ý kiến trái chiều. Cô không ngần ngại bộc bạch \r\nnhững suy nghĩ của mình. Ngọc Trinh cho biết: \"<em>Thực sự em sẽ không \r\nbao giờ hối tiếc về những gì mình nói vì đó là đúng sự thật đối với em, \r\ncũng như chị Phi Thanh Vân có nói rằng nếu nói dối thì sợ một ngày nào \r\nđó người ta sẽ \'khui\' mình ra, em rất sợ điều đó, và em chỉ còn biết là \r\nnói thật thôi ạ\".</em></p>\r\n<p>Ngọc Trinh chia sẻ thêm<em>: “Em nghĩ những ý kiến mà anh nói chỉ là \r\nnhững ý kiến cá nhân thôi chứ không phải là ý kiến chung của tất cả các \r\ncô gái khác. Tại sao những người đó không nghĩ rằng tại sao xã hội lại \r\ntôn vinh em? Nếu xã hội tôn vinh em thì chắc chắn em có một thế mạnh nào\r\n đó\".<br>\r\n</em></p>\r\n<p>\r\n</p>\r\n<div>\r\n<table class=\"image center\" cellpadding=\"0\" cellspacing=\"0\" width=\"400\" align=\"center\">\r\n    <tbody>\r\n        <tr>\r\n            <td>\r\n            <img alt=\"\" src=\"http://imgs.vietnamnet.vn/Images/2012/05/18/11/20120518110503_2.jpg\">\r\n            </td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"image_desc\">\r\n            Nhiều người khen bản chất thật thà của Ngọc Trinh những cũng\r\n không ít người dự đoán, với sự thật thà này của cô, sẽ có một cơn mưa \r\nđá tiếp theo dội xuống đầu cô                    </td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n</div>\r\n<br>\r\nTiếp đến, MC Thanh Bạch lại hỏi Ngọc Trinh câu thứ 2, \"<em>Bây giờ mỗi \r\nlần Google thì không biết bao nhiều bài mà nói về Ngọc Trinh, đếm không \r\nxuể, có khá nhiều dư luận trái chiều và nói bạn \"ngoan\" như thế thì \r\nngười ta không chấp nhận, Ngọc Trinh nghĩ sao?</em>\".<br>\r\n<br>\r\nNgọc Trinh thật thà cho biết: <em>\"Ngay từ nhỏ em đã được ba dạy là phải\r\n sống thật thà, và khi lớn lên em cũng thấy rằng sự thật thà rất cần \r\nthiết trong cuộc sống. Và em cũng thấy là người ta thường thương những \r\nngười thật thà hơn là những người ít thật thà</em>\".<br>\r\n<br>\r\nNhiều khán giả có mặt tại sự kiện đều khá hài lòng với phần trả lời \r\nthẳng thắn và chân thật của Ngọc Trinh. Nhưng không ít người cũng dự \r\nđoán sẽ có một \"cơn mưa đá\" tiếp theo sẽ đổ lên đầu cô gái trẻ này với \r\nphần chia sẻ có đôi phần nhạy cảm. <br>'),(16,'Chelsea vô địch C1','<span style=\"background-color: rgb(0, 255, 204);\">llkk</span><br><img src=\"http://localhost/saigonmoi/web/upload/post/206332783781376.jpg\" width=\"345\"><br>lllk<br><img src=\"http://localhost/saigonmoi/web/upload/post/522777647198116.jpg\" width=\"300\"><img src=\"http://localhost/saigonmoi/web/upload/post/619850743887094.jpg\" width=\"300\"><br>dăd<br><img src=\"http://localhost/saigonmoi/web/upload/post/450603778339953.jpg\" width=\"300\"><br>abc<br><br><img src=\"http://localhost/saigonmoi/web/upload/post/118478002650615.jpg\" width=\"600\"><br>hello<br><br><img src=\"http://localhost/saigonmoi/web/upload/post/880256969075203.jpg\" width=\"740\"><br><br><br><br>'),(17,'test','test<br><img src=\"http://localhost/saigonmoi/web/upload/post/402759610648036.jpg\" width=\"687\"><br>');

/*Table structure for table `email_template` */

DROP TABLE IF EXISTS `email_template`;

CREATE TABLE `email_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL,
  `content` text,
  `last_email` bigint(20) DEFAULT NULL,
  `num_send` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `email_template` */

insert  into `email_template`(`id`,`subject`,`content`,`last_email`,`num_send`,`status`,`deleted`) values (5,'Giới thiệu nhà hàng','Đây là email<span style=\"font-weight: bold;\"> giới thiệu nhà</span> hàng.<br>',0,0,1,0);

/*Table structure for table `emails` */

DROP TABLE IF EXISTS `emails`;

CREATE TABLE `emails` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `emails` */

insert  into `emails`(`id`,`email`,`deleted`) values (1,'nclong87@gmail.com',0),(2,'admin@jobbid.vn',0),(7,'imleeu@gmail.com',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
