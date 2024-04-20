/*
 Navicat Premium Data Transfer

 Source Server         : Mysql3308
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3308
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 02/12/2021 20:49:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cd_admin
-- ----------------------------
DROP TABLE IF EXISTS `cd_admin`;
CREATE TABLE `cd_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_admin
-- ----------------------------
INSERT INTO `cd_admin` VALUES (1, '张主任', 'admin', 'admin');
INSERT INTO `cd_admin` VALUES (2, '阮锦利', 'rjl', 'rjl');

-- ----------------------------
-- Table structure for cd_check
-- ----------------------------
DROP TABLE IF EXISTS `cd_check`;
CREATE TABLE `cd_check`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sno` int(0) NULL DEFAULT NULL,
  `worker_no` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKoebwu0g7q0x0a0n4u8c3iuyk8`(`worker_no`) USING BTREE,
  INDEX `FKjjpi6kbxncuaeg2wfur30lm9e`(`sno`) USING BTREE,
  CONSTRAINT `FKjjpi6kbxncuaeg2wfur30lm9e` FOREIGN KEY (`sno`) REFERENCES `cd_student` (`sno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKoebwu0g7q0x0a0n4u8c3iuyk8` FOREIGN KEY (`worker_no`) REFERENCES `cd_worker` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_check
-- ----------------------------
INSERT INTO `cd_check` VALUES (7, '阴性', '小陈', '李四', 1942, 2);
INSERT INTO `cd_check` VALUES (8, '阳性', '小软', '王五', 1940, 3);

-- ----------------------------
-- Table structure for cd_prepare
-- ----------------------------
DROP TABLE IF EXISTS `cd_prepare`;
CREATE TABLE `cd_prepare`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jkm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sno` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `FKg5odudyiojota6ynhl2p3b1ej`(`sno`) USING BTREE,
  CONSTRAINT `FKg5odudyiojota6ynhl2p3b1ej` FOREIGN KEY (`sno`) REFERENCES `cd_student` (`sno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_prepare
-- ----------------------------
INSERT INTO `cd_prepare` VALUES (24, '北门', 'y', 'test', '已处理', 1941);

-- ----------------------------
-- Table structure for cd_student
-- ----------------------------
DROP TABLE IF EXISTS `cd_student`;
CREATE TABLE `cd_student`  (
  `sno` int(0) NOT NULL AUTO_INCREMENT,
  `classes` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sage` int(0) NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE,
  INDEX `sno`(`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1946 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_student
-- ----------------------------
INSERT INTO `cd_student` VALUES (1940, '19大数据3班', '软件系', 20, '男', '小软');
INSERT INTO `cd_student` VALUES (1941, 'test  ', 'test', 22, '男', 'test');
INSERT INTO `cd_student` VALUES (1942, '19大数据2班', '软件系', 26, '女', '小陈');
INSERT INTO `cd_student` VALUES (1943, '19软工3班', '软件系', 21, '男', '小刘');
INSERT INTO `cd_student` VALUES (1944, '19电计1班', '软件系', 23, '女', '小美');
INSERT INTO `cd_student` VALUES (1945, '19大数据1班', '软件系', 21, '男', '小张');
INSERT INTO `cd_student` VALUES (1946, '19大数据1班', '软件系', 22, '女', '小孔');

-- ----------------------------
-- Table structure for cd_submit
-- ----------------------------
DROP TABLE IF EXISTS `cd_submit`;
CREATE TABLE `cd_submit`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jkm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_sno` int(0) NULL DEFAULT NULL,
  `stu_sno` int(0) NULL DEFAULT NULL,
  `worker_no` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `FKos10o74s9h58u597b6fgcwnwg`(`stu_sno`) USING BTREE,
  INDEX `FKsgudq2tqy12b6a3rfonwl7tph`(`check_sno`) USING BTREE,
  INDEX `FKeey2s45ent1c7yk8buiwtuhyx`(`worker_no`) USING BTREE,
  CONSTRAINT `FKeey2s45ent1c7yk8buiwtuhyx` FOREIGN KEY (`worker_no`) REFERENCES `cd_worker` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKos10o74s9h58u597b6fgcwnwg` FOREIGN KEY (`stu_sno`) REFERENCES `cd_student` (`sno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsgudq2tqy12b6a3rfonwl7tph` FOREIGN KEY (`check_sno`) REFERENCES `cd_check` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_submit
-- ----------------------------
INSERT INTO `cd_submit` VALUES (17, '核酸检测通过', '24-11-2021 22:18:42', 'n', '小陈', '已上报', '李四', 7, 1942, 2);
INSERT INTO `cd_submit` VALUES (19, '核酸检测不通过', '25-11-2021 11:03:58', 'n', '小软', '无法上报', '王五', 8, 1940, 3);
INSERT INTO `cd_submit` VALUES (22, '直接上报', '27-11-2021 21:02:07', 'y', 'test', '已上报', '李四', NULL, 1941, 2);

-- ----------------------------
-- Table structure for cd_worker
-- ----------------------------
DROP TABLE IF EXISTS `cd_worker`;
CREATE TABLE `cd_worker`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `wage` int(0) NULL DEFAULT NULL,
  `wname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wsex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wtype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_worker
-- ----------------------------
INSERT INTO `cd_worker` VALUES (1, 25, '张三', '1001', '男', '南门卫');
INSERT INTO `cd_worker` VALUES (2, 45, '李四', '1002', '男', '北门卫');
INSERT INTO `cd_worker` VALUES (3, 46, '王五', '1003', '男', '核酸检测员');
INSERT INTO `cd_worker` VALUES (4, 53, '赵六', '1004', '女', '南门卫');
INSERT INTO `cd_worker` VALUES (5, 56, '钱七', '1005', '男', '北门卫');

SET FOREIGN_KEY_CHECKS = 1;
