/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云服务器
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 121.5.40.72:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 27/01/2021 22:56:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_comment
-- ----------------------------
DROP TABLE IF EXISTS `pms_comment`;
CREATE TABLE `pms_comment`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_ID` bigint(20) NULL DEFAULT NULL COMMENT '评价ID',
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '商品编号（规则：年份+商铺编号+5位流水号）',
  `PRODUCT_SKU_DATA` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '购买时商品销售属性',
  `COMMENT_CONTENT` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '评价内容',
  `COMMENT_PICS` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评价图片',
  `STAR` tinyint(4) NULL DEFAULT NULL COMMENT '评价星级：0-5',
  `COMMENT_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '评价用户ID',
  `COMMENT_USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评价用户名',
  `COMMENT_USER_ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评价用户头像',
  `COMMENT_TIME` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '商品评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_comment_replay
-- ----------------------------
DROP TABLE IF EXISTS `pms_comment_replay`;
CREATE TABLE `pms_comment_replay`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_ID` bigint(20) NULL DEFAULT NULL COMMENT '评价ID',
  `REPLAY_CONTENT` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '回复内容',
  `REPLAY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '回复用户ID',
  `REPLAY_USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '回复用户名',
  `REPLAY_USER_ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '回复用户头像',
  `REPLAY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '商品评价回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_freight_template
-- ----------------------------
DROP TABLE IF EXISTS `pms_freight_template`;
CREATE TABLE `pms_freight_template`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FREIGHT_TEMPLATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `TEMPLATE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '模板名称',
  `TEMPLATE_TYPE` tinyint(4) NULL DEFAULT NULL COMMENT '1->默认不包邮 2->默认包邮 3->按条件包邮',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '运费模版表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_freight_template_charge
-- ----------------------------
DROP TABLE IF EXISTS `pms_freight_template_charge`;
CREATE TABLE `pms_freight_template_charge`  (
  `ID` bigint(20) NOT NULL,
  `FREIGHT_TEMPLATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `CHARGE_CITY_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收费城市IDS',
  `FIRST_COUNT` bigint(10) NULL DEFAULT NULL COMMENT '首件数量',
  `FIRST_PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '首件价格',
  `CONTINUE_COUNT` bigint(255) NULL DEFAULT NULL COMMENT '续件数量',
  `CONTINUE_PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '续件价格',
  `FREIGHT_TYPE` tinyint(4) NULL DEFAULT NULL COMMENT '运送方式',
  `DEFAULT_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '0->默认（所有城市）1->非默认',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '不包邮运费规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_freight_template_free
-- ----------------------------
DROP TABLE IF EXISTS `pms_freight_template_free`;
CREATE TABLE `pms_freight_template_free`  (
  `ID` bigint(20) NOT NULL,
  `FREIGHT_TEMPLATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `FREE_CITY_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '包邮城市IDS',
  `FREE_COUNT` decimal(10, 2) NULL DEFAULT NULL COMMENT '包邮件数',
  `FREE_PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '包邮金额',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '条件包邮运费规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`  (
  `ID` bigint(20) NOT NULL COMMENT '编号（自增主键）',
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '商品编号（规则：年份+商铺编号+5位流水号）',
  `PRODUCT_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `PRODUCT_DESCRIPTOIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
  `PRODUCT_CATEGORY_ID` bigint(20) NULL DEFAULT NULL COMMENT '商品类目',
  `PRODUCT_CATEGORY_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品类目名称',
  `PRODUCT_PICS` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '产品图片限制为5张，以逗号分割',
  `PRODUCT_DETAIL_HTML` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '网页详情内容',
  `SHOP_ID` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `SHOP_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `DELETE_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
  `PUBLISH_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `NEW_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '新品状态：0->不是新品；1->新品',
  `RECOMMAND_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态：0->未审核；1->审核通过',
  `SALES` bigint(20) NULL DEFAULT NULL COMMENT '销量',
  `PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `STOCK` bigint(20) NULL DEFAULT NULL COMMENT '库存',
  `LOW_STOCK` bigint(20) NULL DEFAULT NULL COMMENT '库存预警值',
  `FREIGHT_TEMPLATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '运费模板ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '商品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute`;
CREATE TABLE `pms_product_attribute`  (
  `ATTRIBUTE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ATTRIBUTE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '产品属性名称',
  PRIMARY KEY (`ATTRIBUTE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '产品参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_attribute_value`;
CREATE TABLE `pms_product_attribute_value`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '产品ID',
  `ATTRIBUTE_ID` bigint(20) NULL DEFAULT NULL COMMENT '产品属性ID',
  `ATTRIBUTE_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '产品参数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '存储产品参数数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENTS_ID` bigint(20) NULL DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
  `CATEGORY_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '类目名称',
  `LEVEL` tinyint(4) NULL DEFAULT NULL COMMENT '类目级别',
  `NAV_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '是否显示在导航栏：0->不显示；1->显示',
  `SHOW_STATUS` tinyint(255) NULL DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `SORT` bigint(20) NULL DEFAULT NULL COMMENT '排序顺序',
  `ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_sku`;
CREATE TABLE `pms_product_sku`  (
  `ID` bigint(20) NOT NULL,
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '商品编号（规则：年份+商铺编号+5位流水号）',
  `SKU_ID` bigint(20) NULL DEFAULT NULL COMMENT '不同规格商品ID',
  `SKU_SALES` bigint(20) NULL DEFAULT NULL COMMENT '销量',
  `SKU_PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `SKU_STOCK` bigint(20) NULL DEFAULT NULL COMMENT '库存',
  `SKU_LOW_STOCK` bigint(20) NULL DEFAULT NULL COMMENT '库存预警值',
  `SKU_PICS` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '不同规格商品图片',
  `SKU_DATA` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '销售属性,JSON格式\r\n[\r\n    {\r\n        \"key\": \"颜色\",\r\n        \"value\": \"黑色\"\r\n    },\r\n    {\r\n        \"key\": \"容量\",\r\n        \"value\": \"32G\"\r\n    }\r\n]\r\n',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_shop
-- ----------------------------
DROP TABLE IF EXISTS `pms_shop`;
CREATE TABLE `pms_shop`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SHOP_ID` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `SHOP_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `SHOP_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '店铺描述',
  `SHOP_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '店主用户ID',
  `SHOP_SCORE` bigint(20) NULL DEFAULT NULL COMMENT '店铺分数',
  `SHOP_STAR` tinyint(4) NULL DEFAULT NULL COMMENT '店铺星级',
  `SHOP_SALES` bigint(20) NULL DEFAULT NULL COMMENT '店铺销量',
  `OPEN_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '开启状态：0->关闭；1->开启',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '店铺表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_shop_fans
-- ----------------------------
DROP TABLE IF EXISTS `pms_shop_fans`;
CREATE TABLE `pms_shop_fans`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SHOP_ID` bigint(20) NULL DEFAULT NULL COMMENT '店铺ID',
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '店铺粉丝表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_city
-- ----------------------------
DROP TABLE IF EXISTS `ums_city`;
CREATE TABLE `ums_city`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CITY_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省市ID',
  `CITY_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省市名称',
  `PARENT_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省市父ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 380 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行政区域地州市信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_city
-- ----------------------------
INSERT INTO `ums_city` VALUES (39, '130100', '石家庄市', '130000');
INSERT INTO `ums_city` VALUES (40, '130200', '唐山市', '130000');
INSERT INTO `ums_city` VALUES (41, '130300', '秦皇岛市', '130000');
INSERT INTO `ums_city` VALUES (42, '130400', '邯郸市', '130000');
INSERT INTO `ums_city` VALUES (43, '130500', '邢台市', '130000');
INSERT INTO `ums_city` VALUES (44, '130600', '保定市', '130000');
INSERT INTO `ums_city` VALUES (45, '130700', '张家口市', '130000');
INSERT INTO `ums_city` VALUES (46, '130800', '承德市', '130000');
INSERT INTO `ums_city` VALUES (47, '130900', '沧州市', '130000');
INSERT INTO `ums_city` VALUES (48, '131000', '廊坊市', '130000');
INSERT INTO `ums_city` VALUES (49, '131100', '衡水市', '130000');
INSERT INTO `ums_city` VALUES (50, '140100', '太原市', '140000');
INSERT INTO `ums_city` VALUES (51, '140200', '大同市', '140000');
INSERT INTO `ums_city` VALUES (52, '140300', '阳泉市', '140000');
INSERT INTO `ums_city` VALUES (53, '140400', '长治市', '140000');
INSERT INTO `ums_city` VALUES (54, '140500', '晋城市', '140000');
INSERT INTO `ums_city` VALUES (55, '140600', '朔州市', '140000');
INSERT INTO `ums_city` VALUES (56, '140700', '晋中市', '140000');
INSERT INTO `ums_city` VALUES (57, '140800', '运城市', '140000');
INSERT INTO `ums_city` VALUES (58, '140900', '忻州市', '140000');
INSERT INTO `ums_city` VALUES (59, '141000', '临汾市', '140000');
INSERT INTO `ums_city` VALUES (60, '141100', '吕梁市', '140000');
INSERT INTO `ums_city` VALUES (61, '150100', '呼和浩特市', '150000');
INSERT INTO `ums_city` VALUES (62, '150200', '包头市', '150000');
INSERT INTO `ums_city` VALUES (63, '150300', '乌海市', '150000');
INSERT INTO `ums_city` VALUES (64, '150400', '赤峰市', '150000');
INSERT INTO `ums_city` VALUES (65, '150500', '通辽市', '150000');
INSERT INTO `ums_city` VALUES (66, '150600', '鄂尔多斯市', '150000');
INSERT INTO `ums_city` VALUES (67, '150700', '呼伦贝尔市', '150000');
INSERT INTO `ums_city` VALUES (68, '150800', '巴彦淖尔市', '150000');
INSERT INTO `ums_city` VALUES (69, '150900', '乌兰察布市', '150000');
INSERT INTO `ums_city` VALUES (70, '152200', '兴安盟', '150000');
INSERT INTO `ums_city` VALUES (71, '152500', '锡林郭勒盟', '150000');
INSERT INTO `ums_city` VALUES (72, '152900', '阿拉善盟', '150000');
INSERT INTO `ums_city` VALUES (73, '210100', '沈阳市', '210000');
INSERT INTO `ums_city` VALUES (74, '210200', '大连市', '210000');
INSERT INTO `ums_city` VALUES (75, '210300', '鞍山市', '210000');
INSERT INTO `ums_city` VALUES (76, '210400', '抚顺市', '210000');
INSERT INTO `ums_city` VALUES (77, '210500', '本溪市', '210000');
INSERT INTO `ums_city` VALUES (78, '210600', '丹东市', '210000');
INSERT INTO `ums_city` VALUES (79, '210700', '锦州市', '210000');
INSERT INTO `ums_city` VALUES (80, '210800', '营口市', '210000');
INSERT INTO `ums_city` VALUES (81, '210900', '阜新市', '210000');
INSERT INTO `ums_city` VALUES (82, '211000', '辽阳市', '210000');
INSERT INTO `ums_city` VALUES (83, '211100', '盘锦市', '210000');
INSERT INTO `ums_city` VALUES (84, '211200', '铁岭市', '210000');
INSERT INTO `ums_city` VALUES (85, '211300', '朝阳市', '210000');
INSERT INTO `ums_city` VALUES (86, '211400', '葫芦岛市', '210000');
INSERT INTO `ums_city` VALUES (87, '220100', '长春市', '220000');
INSERT INTO `ums_city` VALUES (88, '220200', '吉林市', '220000');
INSERT INTO `ums_city` VALUES (89, '220300', '四平市', '220000');
INSERT INTO `ums_city` VALUES (90, '220400', '辽源市', '220000');
INSERT INTO `ums_city` VALUES (91, '220500', '通化市', '220000');
INSERT INTO `ums_city` VALUES (92, '220600', '白山市', '220000');
INSERT INTO `ums_city` VALUES (93, '220700', '松原市', '220000');
INSERT INTO `ums_city` VALUES (94, '220800', '白城市', '220000');
INSERT INTO `ums_city` VALUES (95, '222400', '延边朝鲜族自治州', '220000');
INSERT INTO `ums_city` VALUES (96, '230100', '哈尔滨市', '230000');
INSERT INTO `ums_city` VALUES (97, '230200', '齐齐哈尔市', '230000');
INSERT INTO `ums_city` VALUES (98, '230300', '鸡西市', '230000');
INSERT INTO `ums_city` VALUES (99, '230400', '鹤岗市', '230000');
INSERT INTO `ums_city` VALUES (100, '230500', '双鸭山市', '230000');
INSERT INTO `ums_city` VALUES (101, '230600', '大庆市', '230000');
INSERT INTO `ums_city` VALUES (102, '230700', '伊春市', '230000');
INSERT INTO `ums_city` VALUES (103, '230800', '佳木斯市', '230000');
INSERT INTO `ums_city` VALUES (104, '230900', '七台河市', '230000');
INSERT INTO `ums_city` VALUES (105, '231000', '牡丹江市', '230000');
INSERT INTO `ums_city` VALUES (106, '231100', '黑河市', '230000');
INSERT INTO `ums_city` VALUES (107, '231200', '绥化市', '230000');
INSERT INTO `ums_city` VALUES (108, '232700', '大兴安岭地区', '230000');
INSERT INTO `ums_city` VALUES (111, '320100', '南京市', '320000');
INSERT INTO `ums_city` VALUES (112, '320200', '无锡市', '320000');
INSERT INTO `ums_city` VALUES (113, '320300', '徐州市', '320000');
INSERT INTO `ums_city` VALUES (114, '320400', '常州市', '320000');
INSERT INTO `ums_city` VALUES (115, '320500', '苏州市', '320000');
INSERT INTO `ums_city` VALUES (116, '320600', '南通市', '320000');
INSERT INTO `ums_city` VALUES (117, '320700', '连云港市', '320000');
INSERT INTO `ums_city` VALUES (118, '320800', '淮安市', '320000');
INSERT INTO `ums_city` VALUES (119, '320900', '盐城市', '320000');
INSERT INTO `ums_city` VALUES (120, '321000', '扬州市', '320000');
INSERT INTO `ums_city` VALUES (121, '321100', '镇江市', '320000');
INSERT INTO `ums_city` VALUES (122, '321200', '泰州市', '320000');
INSERT INTO `ums_city` VALUES (123, '321300', '宿迁市', '320000');
INSERT INTO `ums_city` VALUES (124, '330100', '杭州市', '330000');
INSERT INTO `ums_city` VALUES (125, '330200', '宁波市', '330000');
INSERT INTO `ums_city` VALUES (126, '330300', '温州市', '330000');
INSERT INTO `ums_city` VALUES (127, '330400', '嘉兴市', '330000');
INSERT INTO `ums_city` VALUES (128, '330500', '湖州市', '330000');
INSERT INTO `ums_city` VALUES (129, '330600', '绍兴市', '330000');
INSERT INTO `ums_city` VALUES (130, '330700', '金华市', '330000');
INSERT INTO `ums_city` VALUES (131, '330800', '衢州市', '330000');
INSERT INTO `ums_city` VALUES (132, '330900', '舟山市', '330000');
INSERT INTO `ums_city` VALUES (133, '331000', '台州市', '330000');
INSERT INTO `ums_city` VALUES (134, '331100', '丽水市', '330000');
INSERT INTO `ums_city` VALUES (135, '340100', '合肥市', '340000');
INSERT INTO `ums_city` VALUES (136, '340200', '芜湖市', '340000');
INSERT INTO `ums_city` VALUES (137, '340300', '蚌埠市', '340000');
INSERT INTO `ums_city` VALUES (138, '340400', '淮南市', '340000');
INSERT INTO `ums_city` VALUES (139, '340500', '马鞍山市', '340000');
INSERT INTO `ums_city` VALUES (140, '340600', '淮北市', '340000');
INSERT INTO `ums_city` VALUES (141, '340700', '铜陵市', '340000');
INSERT INTO `ums_city` VALUES (142, '340800', '安庆市', '340000');
INSERT INTO `ums_city` VALUES (143, '341000', '黄山市', '340000');
INSERT INTO `ums_city` VALUES (144, '341100', '滁州市', '340000');
INSERT INTO `ums_city` VALUES (145, '341200', '阜阳市', '340000');
INSERT INTO `ums_city` VALUES (146, '341300', '宿州市', '340000');
INSERT INTO `ums_city` VALUES (147, '341400', '巢湖市', '340000');
INSERT INTO `ums_city` VALUES (148, '341500', '六安市', '340000');
INSERT INTO `ums_city` VALUES (149, '341600', '亳州市', '340000');
INSERT INTO `ums_city` VALUES (150, '341700', '池州市', '340000');
INSERT INTO `ums_city` VALUES (151, '341800', '宣城市', '340000');
INSERT INTO `ums_city` VALUES (152, '350100', '福州市', '350000');
INSERT INTO `ums_city` VALUES (153, '350200', '厦门市', '350000');
INSERT INTO `ums_city` VALUES (154, '350300', '莆田市', '350000');
INSERT INTO `ums_city` VALUES (155, '350400', '三明市', '350000');
INSERT INTO `ums_city` VALUES (156, '350500', '泉州市', '350000');
INSERT INTO `ums_city` VALUES (157, '350600', '漳州市', '350000');
INSERT INTO `ums_city` VALUES (158, '350700', '南平市', '350000');
INSERT INTO `ums_city` VALUES (159, '350800', '龙岩市', '350000');
INSERT INTO `ums_city` VALUES (160, '350900', '宁德市', '350000');
INSERT INTO `ums_city` VALUES (161, '360100', '南昌市', '360000');
INSERT INTO `ums_city` VALUES (162, '360200', '景德镇市', '360000');
INSERT INTO `ums_city` VALUES (163, '360300', '萍乡市', '360000');
INSERT INTO `ums_city` VALUES (164, '360400', '九江市', '360000');
INSERT INTO `ums_city` VALUES (165, '360500', '新余市', '360000');
INSERT INTO `ums_city` VALUES (166, '360600', '鹰潭市', '360000');
INSERT INTO `ums_city` VALUES (167, '360700', '赣州市', '360000');
INSERT INTO `ums_city` VALUES (168, '360800', '吉安市', '360000');
INSERT INTO `ums_city` VALUES (169, '360900', '宜春市', '360000');
INSERT INTO `ums_city` VALUES (170, '361000', '抚州市', '360000');
INSERT INTO `ums_city` VALUES (171, '361100', '上饶市', '360000');
INSERT INTO `ums_city` VALUES (172, '370100', '济南市', '370000');
INSERT INTO `ums_city` VALUES (173, '370200', '青岛市', '370000');
INSERT INTO `ums_city` VALUES (174, '370300', '淄博市', '370000');
INSERT INTO `ums_city` VALUES (175, '370400', '枣庄市', '370000');
INSERT INTO `ums_city` VALUES (176, '370500', '东营市', '370000');
INSERT INTO `ums_city` VALUES (177, '370600', '烟台市', '370000');
INSERT INTO `ums_city` VALUES (178, '370700', '潍坊市', '370000');
INSERT INTO `ums_city` VALUES (179, '370800', '济宁市', '370000');
INSERT INTO `ums_city` VALUES (180, '370900', '泰安市', '370000');
INSERT INTO `ums_city` VALUES (181, '371000', '威海市', '370000');
INSERT INTO `ums_city` VALUES (182, '371100', '日照市', '370000');
INSERT INTO `ums_city` VALUES (183, '371200', '莱芜市', '370000');
INSERT INTO `ums_city` VALUES (184, '371300', '临沂市', '370000');
INSERT INTO `ums_city` VALUES (185, '371400', '德州市', '370000');
INSERT INTO `ums_city` VALUES (186, '371500', '聊城市', '370000');
INSERT INTO `ums_city` VALUES (187, '371600', '滨州市', '370000');
INSERT INTO `ums_city` VALUES (188, '371700', '荷泽市', '370000');
INSERT INTO `ums_city` VALUES (189, '410100', '郑州市', '410000');
INSERT INTO `ums_city` VALUES (190, '410200', '开封市', '410000');
INSERT INTO `ums_city` VALUES (191, '410300', '洛阳市', '410000');
INSERT INTO `ums_city` VALUES (192, '410400', '平顶山市', '410000');
INSERT INTO `ums_city` VALUES (193, '410500', '安阳市', '410000');
INSERT INTO `ums_city` VALUES (194, '410600', '鹤壁市', '410000');
INSERT INTO `ums_city` VALUES (195, '410700', '新乡市', '410000');
INSERT INTO `ums_city` VALUES (196, '410800', '焦作市', '410000');
INSERT INTO `ums_city` VALUES (197, '410900', '濮阳市', '410000');
INSERT INTO `ums_city` VALUES (198, '411000', '许昌市', '410000');
INSERT INTO `ums_city` VALUES (199, '411100', '漯河市', '410000');
INSERT INTO `ums_city` VALUES (200, '411200', '三门峡市', '410000');
INSERT INTO `ums_city` VALUES (201, '411300', '南阳市', '410000');
INSERT INTO `ums_city` VALUES (202, '411400', '商丘市', '410000');
INSERT INTO `ums_city` VALUES (203, '411500', '信阳市', '410000');
INSERT INTO `ums_city` VALUES (204, '411600', '周口市', '410000');
INSERT INTO `ums_city` VALUES (205, '411700', '驻马店市', '410000');
INSERT INTO `ums_city` VALUES (206, '420100', '武汉市', '420000');
INSERT INTO `ums_city` VALUES (207, '420200', '黄石市', '420000');
INSERT INTO `ums_city` VALUES (208, '420300', '十堰市', '420000');
INSERT INTO `ums_city` VALUES (209, '420500', '宜昌市', '420000');
INSERT INTO `ums_city` VALUES (210, '420600', '襄樊市', '420000');
INSERT INTO `ums_city` VALUES (211, '420700', '鄂州市', '420000');
INSERT INTO `ums_city` VALUES (212, '420800', '荆门市', '420000');
INSERT INTO `ums_city` VALUES (213, '420900', '孝感市', '420000');
INSERT INTO `ums_city` VALUES (214, '421000', '荆州市', '420000');
INSERT INTO `ums_city` VALUES (215, '421100', '黄冈市', '420000');
INSERT INTO `ums_city` VALUES (216, '421200', '咸宁市', '420000');
INSERT INTO `ums_city` VALUES (217, '421300', '随州市', '420000');
INSERT INTO `ums_city` VALUES (218, '422800', '恩施土家族苗族自治州', '420000');
INSERT INTO `ums_city` VALUES (219, '429000', '省直辖行政单位', '420000');
INSERT INTO `ums_city` VALUES (220, '430100', '长沙市', '430000');
INSERT INTO `ums_city` VALUES (221, '430200', '株洲市', '430000');
INSERT INTO `ums_city` VALUES (222, '430300', '湘潭市', '430000');
INSERT INTO `ums_city` VALUES (223, '430400', '衡阳市', '430000');
INSERT INTO `ums_city` VALUES (224, '430500', '邵阳市', '430000');
INSERT INTO `ums_city` VALUES (225, '430600', '岳阳市', '430000');
INSERT INTO `ums_city` VALUES (226, '430700', '常德市', '430000');
INSERT INTO `ums_city` VALUES (227, '430800', '张家界市', '430000');
INSERT INTO `ums_city` VALUES (228, '430900', '益阳市', '430000');
INSERT INTO `ums_city` VALUES (229, '431000', '郴州市', '430000');
INSERT INTO `ums_city` VALUES (230, '431100', '永州市', '430000');
INSERT INTO `ums_city` VALUES (231, '431200', '怀化市', '430000');
INSERT INTO `ums_city` VALUES (232, '431300', '娄底市', '430000');
INSERT INTO `ums_city` VALUES (233, '433100', '湘西土家族苗族自治州', '430000');
INSERT INTO `ums_city` VALUES (234, '440100', '广州市', '440000');
INSERT INTO `ums_city` VALUES (235, '440200', '韶关市', '440000');
INSERT INTO `ums_city` VALUES (236, '440300', '深圳市', '440000');
INSERT INTO `ums_city` VALUES (237, '440400', '珠海市', '440000');
INSERT INTO `ums_city` VALUES (238, '440500', '汕头市', '440000');
INSERT INTO `ums_city` VALUES (239, '440600', '佛山市', '440000');
INSERT INTO `ums_city` VALUES (240, '440700', '江门市', '440000');
INSERT INTO `ums_city` VALUES (241, '440800', '湛江市', '440000');
INSERT INTO `ums_city` VALUES (242, '440900', '茂名市', '440000');
INSERT INTO `ums_city` VALUES (243, '441200', '肇庆市', '440000');
INSERT INTO `ums_city` VALUES (244, '441300', '惠州市', '440000');
INSERT INTO `ums_city` VALUES (245, '441400', '梅州市', '440000');
INSERT INTO `ums_city` VALUES (246, '441500', '汕尾市', '440000');
INSERT INTO `ums_city` VALUES (247, '441600', '河源市', '440000');
INSERT INTO `ums_city` VALUES (248, '441700', '阳江市', '440000');
INSERT INTO `ums_city` VALUES (249, '441800', '清远市', '440000');
INSERT INTO `ums_city` VALUES (250, '441900', '东莞市', '440000');
INSERT INTO `ums_city` VALUES (251, '442000', '中山市', '440000');
INSERT INTO `ums_city` VALUES (252, '445100', '潮州市', '440000');
INSERT INTO `ums_city` VALUES (253, '445200', '揭阳市', '440000');
INSERT INTO `ums_city` VALUES (254, '445300', '云浮市', '440000');
INSERT INTO `ums_city` VALUES (255, '450100', '南宁市', '450000');
INSERT INTO `ums_city` VALUES (256, '450200', '柳州市', '450000');
INSERT INTO `ums_city` VALUES (257, '450300', '桂林市', '450000');
INSERT INTO `ums_city` VALUES (258, '450400', '梧州市', '450000');
INSERT INTO `ums_city` VALUES (259, '450500', '北海市', '450000');
INSERT INTO `ums_city` VALUES (260, '450600', '防城港市', '450000');
INSERT INTO `ums_city` VALUES (261, '450700', '钦州市', '450000');
INSERT INTO `ums_city` VALUES (262, '450800', '贵港市', '450000');
INSERT INTO `ums_city` VALUES (263, '450900', '玉林市', '450000');
INSERT INTO `ums_city` VALUES (264, '451000', '百色市', '450000');
INSERT INTO `ums_city` VALUES (265, '451100', '贺州市', '450000');
INSERT INTO `ums_city` VALUES (266, '451200', '河池市', '450000');
INSERT INTO `ums_city` VALUES (267, '451300', '来宾市', '450000');
INSERT INTO `ums_city` VALUES (268, '451400', '崇左市', '450000');
INSERT INTO `ums_city` VALUES (269, '460100', '海口市', '460000');
INSERT INTO `ums_city` VALUES (270, '460200', '三亚市', '460000');
INSERT INTO `ums_city` VALUES (271, '469000', '省直辖县级行政单位', '460000');
INSERT INTO `ums_city` VALUES (275, '510100', '成都市', '510000');
INSERT INTO `ums_city` VALUES (276, '510300', '自贡市', '510000');
INSERT INTO `ums_city` VALUES (277, '510400', '攀枝花市', '510000');
INSERT INTO `ums_city` VALUES (278, '510500', '泸州市', '510000');
INSERT INTO `ums_city` VALUES (279, '510600', '德阳市', '510000');
INSERT INTO `ums_city` VALUES (280, '510700', '绵阳市', '510000');
INSERT INTO `ums_city` VALUES (281, '510800', '广元市', '510000');
INSERT INTO `ums_city` VALUES (282, '510900', '遂宁市', '510000');
INSERT INTO `ums_city` VALUES (283, '511000', '内江市', '510000');
INSERT INTO `ums_city` VALUES (284, '511100', '乐山市', '510000');
INSERT INTO `ums_city` VALUES (285, '511300', '南充市', '510000');
INSERT INTO `ums_city` VALUES (286, '511400', '眉山市', '510000');
INSERT INTO `ums_city` VALUES (287, '511500', '宜宾市', '510000');
INSERT INTO `ums_city` VALUES (288, '511600', '广安市', '510000');
INSERT INTO `ums_city` VALUES (289, '511700', '达州市', '510000');
INSERT INTO `ums_city` VALUES (290, '511800', '雅安市', '510000');
INSERT INTO `ums_city` VALUES (291, '511900', '巴中市', '510000');
INSERT INTO `ums_city` VALUES (292, '512000', '资阳市', '510000');
INSERT INTO `ums_city` VALUES (293, '513200', '阿坝藏族羌族自治州', '510000');
INSERT INTO `ums_city` VALUES (294, '513300', '甘孜藏族自治州', '510000');
INSERT INTO `ums_city` VALUES (295, '513400', '凉山彝族自治州', '510000');
INSERT INTO `ums_city` VALUES (296, '520100', '贵阳市', '520000');
INSERT INTO `ums_city` VALUES (297, '520200', '六盘水市', '520000');
INSERT INTO `ums_city` VALUES (298, '520300', '遵义市', '520000');
INSERT INTO `ums_city` VALUES (299, '520400', '安顺市', '520000');
INSERT INTO `ums_city` VALUES (300, '522200', '铜仁地区', '520000');
INSERT INTO `ums_city` VALUES (301, '522300', '黔西南布依族苗族自治州', '520000');
INSERT INTO `ums_city` VALUES (302, '522400', '毕节地区', '520000');
INSERT INTO `ums_city` VALUES (303, '522600', '黔东南苗族侗族自治州', '520000');
INSERT INTO `ums_city` VALUES (304, '522700', '黔南布依族苗族自治州', '520000');
INSERT INTO `ums_city` VALUES (305, '530100', '昆明市', '530000');
INSERT INTO `ums_city` VALUES (306, '530300', '曲靖市', '530000');
INSERT INTO `ums_city` VALUES (307, '530400', '玉溪市', '530000');
INSERT INTO `ums_city` VALUES (308, '530500', '保山市', '530000');
INSERT INTO `ums_city` VALUES (309, '530600', '昭通市', '530000');
INSERT INTO `ums_city` VALUES (310, '530700', '丽江市', '530000');
INSERT INTO `ums_city` VALUES (311, '530800', '思茅市', '530000');
INSERT INTO `ums_city` VALUES (312, '530900', '临沧市', '530000');
INSERT INTO `ums_city` VALUES (313, '532300', '楚雄彝族自治州', '530000');
INSERT INTO `ums_city` VALUES (314, '532500', '红河哈尼族彝族自治州', '530000');
INSERT INTO `ums_city` VALUES (315, '532600', '文山壮族苗族自治州', '530000');
INSERT INTO `ums_city` VALUES (316, '532800', '西双版纳傣族自治州', '530000');
INSERT INTO `ums_city` VALUES (317, '532900', '大理白族自治州', '530000');
INSERT INTO `ums_city` VALUES (318, '533100', '德宏傣族景颇族自治州', '530000');
INSERT INTO `ums_city` VALUES (319, '533300', '怒江傈僳族自治州', '530000');
INSERT INTO `ums_city` VALUES (320, '533400', '迪庆藏族自治州', '530000');
INSERT INTO `ums_city` VALUES (321, '540100', '拉萨市', '540000');
INSERT INTO `ums_city` VALUES (322, '542100', '昌都地区', '540000');
INSERT INTO `ums_city` VALUES (323, '542200', '山南地区', '540000');
INSERT INTO `ums_city` VALUES (324, '542300', '日喀则地区', '540000');
INSERT INTO `ums_city` VALUES (325, '542400', '那曲地区', '540000');
INSERT INTO `ums_city` VALUES (326, '542500', '阿里地区', '540000');
INSERT INTO `ums_city` VALUES (327, '542600', '林芝地区', '540000');
INSERT INTO `ums_city` VALUES (328, '610100', '西安市', '610000');
INSERT INTO `ums_city` VALUES (329, '610200', '铜川市', '610000');
INSERT INTO `ums_city` VALUES (330, '610300', '宝鸡市', '610000');
INSERT INTO `ums_city` VALUES (331, '610400', '咸阳市', '610000');
INSERT INTO `ums_city` VALUES (332, '610500', '渭南市', '610000');
INSERT INTO `ums_city` VALUES (333, '610600', '延安市', '610000');
INSERT INTO `ums_city` VALUES (334, '610700', '汉中市', '610000');
INSERT INTO `ums_city` VALUES (335, '610800', '榆林市', '610000');
INSERT INTO `ums_city` VALUES (336, '610900', '安康市', '610000');
INSERT INTO `ums_city` VALUES (337, '611000', '商洛市', '610000');
INSERT INTO `ums_city` VALUES (338, '620100', '兰州市', '620000');
INSERT INTO `ums_city` VALUES (339, '620200', '嘉峪关市', '620000');
INSERT INTO `ums_city` VALUES (340, '620300', '金昌市', '620000');
INSERT INTO `ums_city` VALUES (341, '620400', '白银市', '620000');
INSERT INTO `ums_city` VALUES (342, '620500', '天水市', '620000');
INSERT INTO `ums_city` VALUES (343, '620600', '武威市', '620000');
INSERT INTO `ums_city` VALUES (344, '620700', '张掖市', '620000');
INSERT INTO `ums_city` VALUES (345, '620800', '平凉市', '620000');
INSERT INTO `ums_city` VALUES (346, '620900', '酒泉市', '620000');
INSERT INTO `ums_city` VALUES (347, '621000', '庆阳市', '620000');
INSERT INTO `ums_city` VALUES (348, '621100', '定西市', '620000');
INSERT INTO `ums_city` VALUES (349, '621200', '陇南市', '620000');
INSERT INTO `ums_city` VALUES (350, '622900', '临夏回族自治州', '620000');
INSERT INTO `ums_city` VALUES (351, '623000', '甘南藏族自治州', '620000');
INSERT INTO `ums_city` VALUES (352, '630100', '西宁市', '630000');
INSERT INTO `ums_city` VALUES (353, '632100', '海东地区', '630000');
INSERT INTO `ums_city` VALUES (354, '632200', '海北藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (355, '632300', '黄南藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (356, '632500', '海南藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (357, '632600', '果洛藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (358, '632700', '玉树藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (359, '632800', '海西蒙古族藏族自治州', '630000');
INSERT INTO `ums_city` VALUES (360, '640100', '银川市', '640000');
INSERT INTO `ums_city` VALUES (361, '640200', '石嘴山市', '640000');
INSERT INTO `ums_city` VALUES (362, '640300', '吴忠市', '640000');
INSERT INTO `ums_city` VALUES (363, '640400', '固原市', '640000');
INSERT INTO `ums_city` VALUES (364, '640500', '中卫市', '640000');
INSERT INTO `ums_city` VALUES (365, '650100', '乌鲁木齐市', '650000');
INSERT INTO `ums_city` VALUES (366, '650200', '克拉玛依市', '650000');
INSERT INTO `ums_city` VALUES (367, '652100', '吐鲁番地区', '650000');
INSERT INTO `ums_city` VALUES (368, '652200', '哈密地区', '650000');
INSERT INTO `ums_city` VALUES (369, '652300', '昌吉回族自治州', '650000');
INSERT INTO `ums_city` VALUES (370, '652700', '博尔塔拉蒙古自治州', '650000');
INSERT INTO `ums_city` VALUES (371, '652800', '巴音郭楞蒙古自治州', '650000');
INSERT INTO `ums_city` VALUES (372, '652900', '阿克苏地区', '650000');
INSERT INTO `ums_city` VALUES (373, '653000', '克孜勒苏柯尔克孜自治州', '650000');
INSERT INTO `ums_city` VALUES (374, '653100', '喀什地区', '650000');
INSERT INTO `ums_city` VALUES (375, '653200', '和田地区', '650000');
INSERT INTO `ums_city` VALUES (376, '654000', '伊犁哈萨克自治州', '650000');
INSERT INTO `ums_city` VALUES (377, '654200', '塔城地区', '650000');
INSERT INTO `ums_city` VALUES (378, '654300', '阿勒泰地区', '650000');
INSERT INTO `ums_city` VALUES (379, '659000', '省直辖行政单位', '650000');
INSERT INTO `ums_city` VALUES (1, '110000', '北京市', '0');
INSERT INTO `ums_city` VALUES (2, '120000', '天津市', '0');
INSERT INTO `ums_city` VALUES (3, '130000', '河北省', '0');
INSERT INTO `ums_city` VALUES (4, '140000', '山西省', '0');
INSERT INTO `ums_city` VALUES (5, '150000', '内蒙古自治区', '0');
INSERT INTO `ums_city` VALUES (6, '210000', '辽宁省', '0');
INSERT INTO `ums_city` VALUES (7, '220000', '吉林省', '0');
INSERT INTO `ums_city` VALUES (8, '230000', '黑龙江省', '0');
INSERT INTO `ums_city` VALUES (9, '310000', '上海市', '0');
INSERT INTO `ums_city` VALUES (10, '320000', '江苏省', '0');
INSERT INTO `ums_city` VALUES (11, '330000', '浙江省', '0');
INSERT INTO `ums_city` VALUES (12, '340000', '安徽省', '0');
INSERT INTO `ums_city` VALUES (13, '350000', '福建省', '0');
INSERT INTO `ums_city` VALUES (14, '360000', '江西省', '0');
INSERT INTO `ums_city` VALUES (15, '370000', '山东省', '0');
INSERT INTO `ums_city` VALUES (16, '410000', '河南省', '0');
INSERT INTO `ums_city` VALUES (17, '420000', '湖北省', '0');
INSERT INTO `ums_city` VALUES (18, '430000', '湖南省', '0');
INSERT INTO `ums_city` VALUES (19, '440000', '广东省', '0');
INSERT INTO `ums_city` VALUES (20, '450000', '广西壮族自治区', '0');
INSERT INTO `ums_city` VALUES (21, '460000', '海南省', '0');
INSERT INTO `ums_city` VALUES (22, '500000', '重庆市', '0');
INSERT INTO `ums_city` VALUES (23, '510000', '四川省', '0');
INSERT INTO `ums_city` VALUES (24, '520000', '贵州省', '0');
INSERT INTO `ums_city` VALUES (25, '530000', '云南省', '0');
INSERT INTO `ums_city` VALUES (26, '540000', '西藏自治区', '0');
INSERT INTO `ums_city` VALUES (27, '610000', '陕西省', '0');
INSERT INTO `ums_city` VALUES (28, '620000', '甘肃省', '0');
INSERT INTO `ums_city` VALUES (29, '630000', '青海省', '0');
INSERT INTO `ums_city` VALUES (30, '640000', '宁夏回族自治区', '0');
INSERT INTO `ums_city` VALUES (31, '650000', '新疆维吾尔自治区', '0');
INSERT INTO `ums_city` VALUES (32, '710000', '台湾省', '0');
INSERT INTO `ums_city` VALUES (33, '810000', '香港特别行政区', '0');
INSERT INTO `ums_city` VALUES (34, '820000', '澳门特别行政区', '0');

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `VALUE` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权限值',
  `ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `TYPE` tinyint(4) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `URI` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `SORT` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, 0, '用户管理', NULL, NULL, 0, NULL, 1, '2021-01-25 14:39:41', 0);
INSERT INTO `ums_permission` VALUES (2, 1, '查询用户', 'user:read', NULL, 1, 'user/index', 1, '2021-01-25 14:40:43', 0);
INSERT INTO `ums_permission` VALUES (3, 1, '修改用户', 'user:update', '', 1, 'user/index', 1, '2021-01-25 14:41:32', 0);
INSERT INTO `ums_permission` VALUES (4, 1, '删除用户', 'user:delete', NULL, 1, 'user/index', 1, '2021-01-25 14:42:12', 0);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `USER_COUNT` bigint(20) NULL DEFAULT NULL COMMENT '后台用户数量',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '启用状态：0->禁用；1->启用',
  `SORT` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, '超级管理员', '超级管理员', 1, '2021-01-25 14:38:50', 1, 0);

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `PERMISSION_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `ums_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `ums_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `ums_role_permission_relation` VALUES (4, 1, 4);

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `EMAIL` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `MOBILE` bigint(20) NULL DEFAULT NULL COMMENT '手机号',
  `NICK_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `GENDER` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `CITY` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '城市',
  `JOB` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '职业',
  `NOTE` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注信息',
  `REGISTER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '帐号启用状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 803272115355783168, 'jianyufeng', '$2a$10$uj64yOm/sWh7sz9BXGEQ9OY12M2gcc4kggyJB59ev92ueVw7TFlci', 'http://121.5.40.72:9000/shop/2021-01-25/古拉顿.jpg', '785923055@qq.com', 13911992499, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-25 14:36:36', '2021-01-25 21:02:26', 1);

-- ----------------------------
-- Table structure for ums_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_login_log`;
CREATE TABLE `ums_user_login_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `IP` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'IP',
  `LOGIN_CHANNEL` tinyint(4) NULL DEFAULT NULL COMMENT '登录类型 1-PC,2-安卓3-IOS,4-小程序,5-未知',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_login_log
-- ----------------------------
INSERT INTO `ums_user_login_log` VALUES (1, 803272115355783168, '0:0:0:0:0:0:0:1', 5, '2021-01-25 14:51:06');
INSERT INTO `ums_user_login_log` VALUES (2, 803272115355783168, '0:0:0:0:0:0:0:1', 5, '2021-01-25 15:12:20');
INSERT INTO `ums_user_login_log` VALUES (3, 803272115355783168, '0:0:0:0:0:0:0:1', 5, '2021-01-25 21:02:26');

-- ----------------------------
-- Table structure for ums_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_permission_relation`;
CREATE TABLE `ums_user_permission_relation`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL,
  `PERMISSION_ID` bigint(20) NULL DEFAULT NULL,
  `TYPE` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_product_collect
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_product_collect`;
CREATE TABLE `ums_user_product_collect`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '商品编号（规则：年份+商铺编号+5位流水号）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户商品收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_receive_address`;
CREATE TABLE `ums_user_receive_address`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收货人名称',
  `MOBILE` bigint(20) NULL DEFAULT NULL COMMENT '手机号',
  `DEFAULT_STATUS` tinyint(4) NULL DEFAULT NULL COMMENT '是否为默认',
  `POST_CODE` bigint(20) NULL DEFAULT NULL COMMENT '邮政编码',
  `PROVINCE` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
  `CITY` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '城市',
  `REGION` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区',
  `DETAIL_ADDRESS` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_receive_address
-- ----------------------------
INSERT INTO `ums_user_receive_address` VALUES (1, 803272115355783168, '菅宇峰', 13911992499, 1, 100103, '北京市', '北京', '朝阳区', '来北家园');

-- ----------------------------
-- Table structure for ums_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role_relation`;
CREATE TABLE `ums_user_role_relation`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NULL DEFAULT NULL,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_role_relation
-- ----------------------------
INSERT INTO `ums_user_role_relation` VALUES (1, 803272115355783168, 1);

SET FOREIGN_KEY_CHECKS = 1;
