/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : cloud-mall

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/05/2020 17:58:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mall_permission
-- ----------------------------
DROP TABLE IF EXISTS `mall_permission`;
CREATE TABLE `mall_permission`  (
  `permission_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '权限的id',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限的标识符',
  `permission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识符的描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10019 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_permission
-- ----------------------------
INSERT INTO `mall_permission` VALUES (1, 'back:permission:save', '保存权限', '2020-04-30 06:34:35', '2020-04-30 16:41:07');
INSERT INTO `mall_permission` VALUES (10002, 'back:permission:details', '权限详情', '2020-05-02 16:33:21', '2020-05-02 16:33:21');
INSERT INTO `mall_permission` VALUES (10003, 'back:permission:update', '更新权限标识符', '2020-05-02 16:33:52', '2020-05-02 16:33:52');
INSERT INTO `mall_permission` VALUES (10004, 'back:permission:delete', '删除权限标识符', '2020-05-02 16:34:14', '2020-05-02 16:34:14');
INSERT INTO `mall_permission` VALUES (10005, 'back:permission:query', '权限标识符列表', '2020-05-02 16:34:35', '2020-05-02 16:34:35');
INSERT INTO `mall_permission` VALUES (10006, 'back:role:save', '保存角色', '2020-05-02 16:35:56', '2020-05-02 16:35:56');
INSERT INTO `mall_permission` VALUES (10007, 'back:role:delete', '删除角色', '2020-05-02 16:36:16', '2020-05-02 16:36:16');
INSERT INTO `mall_permission` VALUES (10008, 'back:role:details', '角色详情', '2020-05-02 16:36:38', '2020-05-02 16:36:38');
INSERT INTO `mall_permission` VALUES (10009, 'back:role:update', '角色更新', '2020-05-02 16:36:54', '2020-05-02 16:36:54');
INSERT INTO `mall_permission` VALUES (10010, 'back:role:query', '角色列表', '2020-05-02 16:37:14', '2020-05-02 16:37:14');
INSERT INTO `mall_permission` VALUES (10011, 'back:role:permission:set', '角色分配权限', '2020-05-02 16:37:43', '2020-05-02 16:37:43');
INSERT INTO `mall_permission` VALUES (10012, 'back:role:view:permission', '角色展示权限标识', '2020-05-02 16:38:33', '2020-05-02 16:38:33');
INSERT INTO `mall_permission` VALUES (10013, 'back:user:query', '查询用户列表', '2020-05-02 16:38:59', '2020-05-02 16:38:59');
INSERT INTO `mall_permission` VALUES (10014, 'back:user:details', '用户详情', '2020-05-02 16:39:18', '2020-05-02 16:39:18');
INSERT INTO `mall_permission` VALUES (10015, 'back:admin:update', '后台管理员更新用户信息', '2020-05-02 16:39:37', '2020-05-02 16:40:44');
INSERT INTO `mall_permission` VALUES (10016, 'back:admin:password', '后台管理员修改密码', '2020-05-02 16:41:14', '2020-05-02 16:41:14');
INSERT INTO `mall_permission` VALUES (10017, 'back:user:role:set', '用户分配角色', '2020-05-02 16:41:34', '2020-05-02 16:41:34');
INSERT INTO `mall_permission` VALUES (10018, 'back:user:roles', '用户的角色信息', '2020-05-02 16:41:50', '2020-05-02 16:41:50');

-- ----------------------------
-- Table structure for mall_role
-- ----------------------------
DROP TABLE IF EXISTS `mall_role`;
CREATE TABLE `mall_role`  (
  `role_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '角色的id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色的名字',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色的code编码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_role
-- ----------------------------
INSERT INTO `mall_role` VALUES (1, '管理员', 'admin', '2020-05-02 13:57:36', '2020-05-02 14:19:33');

-- ----------------------------
-- Table structure for mall_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mall_role_permission`;
CREATE TABLE `mall_role_permission`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_role_permission
-- ----------------------------
INSERT INTO `mall_role_permission` VALUES (1, 1);
INSERT INTO `mall_role_permission` VALUES (1, 10016);
INSERT INTO `mall_role_permission` VALUES (1, 10017);
INSERT INTO `mall_role_permission` VALUES (1, 10018);
INSERT INTO `mall_role_permission` VALUES (1, 10002);
INSERT INTO `mall_role_permission` VALUES (1, 10003);
INSERT INTO `mall_role_permission` VALUES (1, 10004);
INSERT INTO `mall_role_permission` VALUES (1, 10005);
INSERT INTO `mall_role_permission` VALUES (1, 10006);
INSERT INTO `mall_role_permission` VALUES (1, 10007);
INSERT INTO `mall_role_permission` VALUES (1, 10008);
INSERT INTO `mall_role_permission` VALUES (1, 10009);
INSERT INTO `mall_role_permission` VALUES (1, 10010);
INSERT INTO `mall_role_permission` VALUES (1, 10011);
INSERT INTO `mall_role_permission` VALUES (1, 10012);
INSERT INTO `mall_role_permission` VALUES (1, 10013);
INSERT INTO `mall_role_permission` VALUES (1, 10014);
INSERT INTO `mall_role_permission` VALUES (1, 10015);

-- ----------------------------
-- Table structure for mall_role_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_role_user`;
CREATE TABLE `mall_role_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_role_user
-- ----------------------------
INSERT INTO `mall_role_user` VALUES (1, 1);

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户的id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户的密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的别名',
  `head_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的头像',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的手机号',
  `sex` int(2) NULL DEFAULT NULL COMMENT '1-男，2-女',
  `type` int(11) NULL DEFAULT NULL COMMENT '1-app前端用户，2-后端用户',
  `enable` int(2) NULL DEFAULT NULL COMMENT '1-启用，2-禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '用户创建的时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '用户更新的时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES (1, 'xiahua', '$2a$10$Rzgn9u4mjxPfaLLJIy/Bnem2ut6tI.rlPsMIrvHB3yKVQMCqzUWLi', '胖虎', NULL, '15639037297', 1, 2, 1, '2020-04-27 15:17:24', '2020-05-02 15:53:42');

SET FOREIGN_KEY_CHECKS = 1;
