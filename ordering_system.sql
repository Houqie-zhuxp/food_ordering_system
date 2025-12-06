/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : ordering_system

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/08/2024 16:32:24
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '管理员', 'http://localhost:9441/files/download/03.jpg', 'ADMIN');
INSERT INTO `admin` VALUES (8, 'admin3', '123456', '管理员2', 'http://localhost:9441/files/download/04.jpg', 'ADMIN');

-- ----------------------------
-- Table structure for foods
-- ----------------------------
DROP TABLE IF EXISTS `foods`;
CREATE TABLE `foods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `descr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '餐品简介',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foods
-- ----------------------------
INSERT INTO `foods` VALUES (3, '腊汁肉', '材料：五花肉、蜂蜜、料酒、色拉油、老抽、葱段、姜片、A料（大料水，酱油，冰糖，生抽、美极鲜味汁，鸡粉，味精）。', 38.00, 'http://localhost:9441/files/download/04_1724487606598.jpg');
INSERT INTO `foods` VALUES (4, '泡菜米椒腰花', '材料：猪腰、小米椒圈、杭椒圈、泡椒丁、泡姜丁、泡萝卜丁、盐、白胡椒粉、料酒、白糖、香醋、味精、鸡粉、水淀粉、鲜汤、色拉油各适量。', 28.88, 'http://localhost:9441/files/download/05_1724487602681.jpg');
INSERT INTO `foods` VALUES (5, '酱椒蒸猪脚', '取猪手，去毛洗净，切成两半，用细流水冲泡30分钟后飞水，捞出入高压锅中，加清水至刚刚没过猪手，下入白醋300克和适量的盐、姜片、葱段，压制7分钟。', 38.00, 'http://localhost:9441/files/download/02_1724487597298.jpg');
INSERT INTO `foods` VALUES (6, '虎皮椒土猪肉', '材料：辣椒、土猪肉、小米椒、香葱、老抽、蚝油、盐、油、生抽、鸡精、味精。', 28.88, 'http://localhost:9441/files/download/03_1724487592374.jpg');
INSERT INTO `foods` VALUES (7, '黄焖鸭', '材料：土仔鸭、小土豆、郫县豆瓣、酱油、芝麻酱、味精、鸡精、白糖、秘制酱。', 38.00, 'http://localhost:9441/files/download/01_1724487585999.jpg');
INSERT INTO `foods` VALUES (8, '孜然香棒骨', '材料：寸筋骨、五香卤水锅、海椒面、孜然粉、花椒油、香油。', 18.88, 'http://localhost:9441/files/download/04_1724487575152.jpg');
INSERT INTO `foods` VALUES (9, '农家烧排骨', '材料：排骨，甜玉米，豆角，花生米、姜、小葱、冰糖、生抽、料酒、八角一块，桂皮、盐、食用油适量。', 28.00, 'http://localhost:9441/files/download/04_1724487570438.jpg');
INSERT INTO `foods` VALUES (10, '栗子红烧鸡', '材料：活雏鸡、生栗子肉、A料（料克，酱油），B料（干淀粉，鸡蛋，料酒、盐），小料（桂皮，八角，葱段，姜片），C料（盐、料酒，酱油），清汤，湿淀粉、芝麻油各，菜子油。', 38.00, 'http://localhost:9441/files/download/01_1724487563064.jpg');
INSERT INTO `foods` VALUES (11, '农家口袋豆腐', '材料：大豆腐、猪肉馅、尖椒、胡萝卜段、鸡蛋、A料（盐，味精、鸡粉），淀粉、白糖、B料（辣椒油，葱、姜），熟豆油，老汤。', 18.88, 'http://localhost:9441/files/download/06_1724487557858.jpg');
INSERT INTO `foods` VALUES (12, '外婆炒鸡', '材料:农家土鸡、鸡杂（鸡胗、鸡肝、鸡肠）、鸡腰、A料（八角、花椒、香叶，肉蔻，桂皮），干红辣椒节、香葱、海天老抽、葱段、姜片、蒜粒、东古一品鲜酱油、胡椒粉、料酒、芝麻油、精盐、味精、开水、菜子油。', 38.00, 'http://localhost:9441/files/download/05_1724487552795.jpg');
INSERT INTO `foods` VALUES (13, '四杯鸡翅', '材料：鸡翅、苦苣、红椒丝、A料（盐，料酒、葱段），可乐、酱油、啤酒、冰糖、海鲜酱、色拉油、芝麻。', 20.00, 'http://localhost:9441/files/download/02_1724487547327.jpg');
INSERT INTO `foods` VALUES (14, '板栗焖老鸭', '材料：老麻鸭、板栗、老姜片、小葱节、盐、酱油、料酒、白糖、味精、菜油适量。', 28.00, 'http://localhost:9441/files/download/03_1724487540346.jpg');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单内容',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '点餐人',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '点餐时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, '小笼包x1，小龙虾x1', 200.00, 1, '2024-07-10 00:01:24', '待出餐', '9aff2f0b0d864ed6b649c817cb78f7bf');
INSERT INTO `orders` VALUES (3, '小笼包x1，小龙虾x1', 200.00, 1, '2024-07-10 00:03:05', '待出餐', 'e7407fd82cb94fc2bd8995b0073c34b4');
INSERT INTO `orders` VALUES (4, '小笼包x1，小龙虾x1', 200.00, 1, '2024-07-10 00:13:03', '已出餐', 'ca9269ae7b62428f9bae499054ad9b7b');
INSERT INTO `orders` VALUES (6, '小龙虾x2', 376.00, 1, '2024-07-10 08:48:39', '已出餐', 'c86334cedc2b4a109fde00655d9bb0e9');
INSERT INTO `orders` VALUES (7, '小笼包x2', 24.00, 1, '2024-07-10 08:52:35', '已出餐', 'bcdab9b087db44819c18e0938aa1db42');
INSERT INTO `orders` VALUES (8, '小笼包x2', 24.00, 1, '2024-07-10 08:59:23', '已出餐', 'ce4321a905924d2b87c861a54f71c11f');
INSERT INTO `orders` VALUES (9, '小笼包x4', 48.00, 1, '2024-07-10 09:00:12', '已出餐', '7fc8e48106384c31a872219ed624beef');
INSERT INTO `orders` VALUES (10, '小笼包x4', 48.00, 1, '2024-07-10 09:02:00', '已出餐', '5428470ad5174ab697b9bde92ac8ddd7');
INSERT INTO `orders` VALUES (11, '外婆炒鸡x1，四杯鸡翅x1，孜然香棒骨x2，酱椒蒸猪脚x2', 171.76, 2, '2024-07-10 09:41:06', '已出餐', '5a71aa0be6b34b988e51a8d900fc4111');
INSERT INTO `orders` VALUES (12, '农家烧排骨x1，孜然香棒骨x1，腊汁肉x1', 84.88, 3, '2024-07-10 09:42:06', '已出餐', '70c3c803e6dc4284bcfec68bd61b18c6');

-- ----------------------------
-- Table structure for tables
-- ----------------------------
DROP TABLE IF EXISTS `tables`;
CREATE TABLE `tables`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '餐桌号',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格',
  `free` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否空闲',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '餐桌信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tables
-- ----------------------------
INSERT INTO `tables` VALUES (1, 'A101', '4人桌', '是', NULL);
INSERT INTO `tables` VALUES (2, 'A102', '4人桌', '是', NULL);
INSERT INTO `tables` VALUES (3, 'B101', '8人桌', '是', NULL);
INSERT INTO `tables` VALUES (4, 'A103', '4人桌', '是', NULL);
INSERT INTO `tables` VALUES (5, 'A104', '4人桌', '是', NULL);
INSERT INTO `tables` VALUES (6, 'B102', '8人桌', '是', NULL);
INSERT INTO `tables` VALUES (7, 'B103', '8人桌', '是', NULL);
INSERT INTO `tables` VALUES (8, 'C101', '12人桌', '是', NULL);
INSERT INTO `tables` VALUES (9, 'C102', '12人桌', '是', NULL);
INSERT INTO `tables` VALUES (10, 'C103', '12人桌', '是', NULL);
INSERT INTO `tables` VALUES (11, 'C103', '12人桌', '是', NULL);
INSERT INTO `tables` VALUES (12, 'D101', '2人桌', '是', NULL);
INSERT INTO `tables` VALUES (13, 'D102', '2人桌', '是', NULL);
INSERT INTO `tables` VALUES (14, 'D103', '2人桌', '否', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `account` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '123456', '张三', 'http://localhost:9441/files/download/05.jpg', 'USER', '女', '13434566754', 152.00);
INSERT INTO `user` VALUES (2, 'lisi', '123456', '李四', 'http://localhost:9441/files/download/02.jpg', 'USER', '男', '15543356654', 328.24);
INSERT INTO `user` VALUES (3, 'wangwu', '123456', '王五', 'http://localhost:9441/files/download/06.jpg', 'USER', '女', '12321423532', 39.12);
INSERT INTO `user` VALUES (6, 'zhaoliu', '123456', '赵六', 'http://localhost:9441/files/download/01.jpg', 'USER', '男', '1432323455', 0.00);

SET FOREIGN_KEY_CHECKS = 1;
