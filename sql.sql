/*缴费记录表添加两个字段*/
ALTER TABLE `payment`
ADD COLUMN paylessonnumber INT COMMENT '缴费课时';
ALTER TABLE `payment`
ADD COLUMN benefactorlessonnumber INT COMMENT '赠送课时';

/*添加课时记录表*/
DROP  TABLE  IF EXISTS hourrecord;
CREATE  TABLE hourrecord(
  hourrecordid INT AUTO_INCREMENT PRIMARY KEY COMMENT '编号主键自增',
  StudentNumber VARCHAR(100)  NOT NULL  COMMENT '学生编号',
  PaymentPeriodNumber VARCHAR(100) NOT NULL  COMMENT  '缴费期编号',
  residue INT DEFAULT 0 COMMENT '剩余课时',
  state INT NOT NULL COMMENT '状态',
  updatetime DATETIME NOT NULL  COMMENT '最后修改时间'
)COMMENT  '课时记录表';

/*缴费期添加两个字段*/
ALTER TABLE `payoutperiod`
  ADD COLUMN paylessonnumber INT COMMENT '缴费课时';
ALTER TABLE `payoutperiod`
  ADD COLUMN benefactorlessonnumber INT COMMENT '赠送课时';

/*课时日志表*/
CREATE TABLE hourlog(
  hourlogid INT AUTO_INCREMENT PRIMARY KEY COMMENT 'id自增主键',
  hourrecordid INT NOT NULL COMMENT '对应课时记录id',
  operationtime DATETIME COMMENT  '操作时间',
  operationtype VARCHAR(200) COMMENT  '操作类型 缴费还是作废缴费',
  quantity int COMMENT  '操作数量',
  donate int COMMENT  '赠送的课时是多少'
) COMMENT  '课时日志表';


/*课时日志添加一个字段操作用户*/
ALTER TABLE `hourlog`
  ADD COLUMN userid VARCHAR(100) COMMENT '操作用户';
/*添加一个缴费记录id*/
ALTER TABLE `hourlog`
  ADD COLUMN PaymentInformationNumber VARCHAR(100) COMMENT '缴费信息id';

/*添加模块 课时记录管理*/
INSERT INTO `menu`(`MenuValue`,`MenuName`,`TheFatherOfMenuId`)
VALUES('gongneng:studentHourrecord','课时记录管理',34);










