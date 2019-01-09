

DROP PROCEDURE IF EXISTS daliy_transaction;
DELIMITER $$
CREATE PROCEDURE daliy_transaction(in Fund1PriceDayliy DECIMAL,in Fund2PriceDayliy DECIMAL,in Fund3PriceDayliy DECIMAL,in today int)
BEGIN
    DECLARE BUYMoney1 DECIMAL DEFAULT 0;
    DECLARE SELLMoney1 DECIMAL DEFAULT 0;
    DECLARE BUYMoney2 DECIMAL DEFAULT 0;
    DECLARE SELLMoney2 DECIMAL DEFAULT 0;
    DECLARE BUYMoney3 DECIMAL DEFAULT 0;
    DECLARE SELLMoney3 DECIMAL DEFAULT 0;
    IF Fund1PriceDayliy = 0 THEN
         select "please enter the Fund1PriceDayliy";
    ELSEIF Fund2PriceDayliy = 0 THEN
         select "please enter the Fund2PriceDayliy";
    ELSEIF Fund3PriceDayliy=0 THEN
         select "please enter the Fund3PriceDayliy";
    ELSEIF today=0 THEN
         select "please enter the right day";
    ELSE
    
        ##更新今日成交记录信息
            ##1.0更新买入信息
        UPDATE `FUND_TRANSACTION` SET fund_price = Fund1PriceDayliy,fund_count=money/Fund1PriceDayliy WHERE fund_id = 1 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY";
        UPDATE `FUND_TRANSACTION` SET fund_price = Fund2PriceDayliy,fund_count=money/Fund2PriceDayliy WHERE fund_id = 2 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY";
        UPDATE `FUND_TRANSACTION` SET fund_price = Fund3PriceDayliy,fund_count=money/Fund3PriceDayliy WHERE fund_id = 3 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY";
            ##2.0更新卖出信息
        UPDATE `FUND_TRANSACTION` SET fund_price = Fund1PriceDayliy,trader_money=fund_count*Fund1PriceDayliy ,money =fund_count*Fund1PriceDayliy*(1-management_fee) WHERE fund_id = 1 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL";
            UPDATE `FUND_TRANSACTION` SET fund_price = Fund2PriceDayliy,trader_money=fund_count*Fund2PriceDayliy ,money =fund_count*Fund2PriceDayliy*(1-management_fee) WHERE fund_id = 2 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL";
            UPDATE `FUND_TRANSACTION` SET fund_price = Fund3PriceDayliy,trader_money=fund_count*Fund3PriceDayliy ,money =fund_count*Fund3PriceDayliy*(1-management_fee) WHERE fund_id = 3 and today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL";
            
        ##获取今日买入成交量1
        SELECT IFNULL(sum(money),0) INTO BUYMoney1 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY" and fund_id=1;
        
        ##获取今日卖出成交量1
        SELECT IFNULL(sum(money),0) INTO SELLMoney1 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL" and fund_id=1;
        
        INSERT INTO `FUND_PRICE`(fund_id,today_price,total_money,today_increase,today_inmoney,today_outmoney) VALUES(1,Fund1PriceDayliy,BUYMoney1+SELLMoney1, BUYMoney1-SELLMoney1, buyMoney1,SELLMoney1);
        
        ##获取今日买入成交量2
        SELECT IFNULL(sum(money),0) INTO BUYMoney2 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY" and fund_id=2;
        
        ##获取今日卖出成交量2
        SELECT IFNULL(sum(money),0) INTO SELLMoney2 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL" and fund_id=2;
        
        INSERT INTO `FUND_PRICE`(fund_id,today_price,total_money,today_increase,today_inmoney,today_outmoney) VALUES(2,Fund2PriceDayliy,BUYMoney2+SELLMoney2, BUYMoney2-SELLMoney2, buyMoney2,SELLMoney2);
        
        ##获取今日买入成交量3
        SELECT IFNULL(sum(money),0) INTO BUYMoney3 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="BUY" and fund_id=3;
        
        ##获取今日卖出成交量3
        SELECT IFNULL(sum(money),0) INTO SELLMoney3 FROM FUND_TRANSACTION WHERE today-(unix_timestamp(transaction_date))<86400 and status=0 and type="SELL" and fund_id=3;
                
        INSERT INTO `FUND_PRICE`(fund_id,today_price,total_money,today_increase,today_inmoney,today_outmoney) VALUES(3,Fund3PriceDayliy,BUYMoney3+SELLMoney3, BUYMoney3-SELLMoney3, buyMoney3,SELLMoney3);
        
        update USER_WALLET a inner join(select user_id,fund_id,sum(trader_money) as m,sum(fund_count) as count from FUND_TRANSACTION b where unix_timestamp('2018-12-29 21:30:00')-unix_timestamp(`transaction_date`)<=86400 and `trader_money`>0 and status=0 and type='BUY' group by user_id,fund_id) c on a.user_id = c.user_id and a.`type`=CONCAT('FUND_',c.fund_id) set a.money = a.money-c.m ,a.count=a.count+c.count;
        
        update USER_WALLET a inner join(select user_id,fund_id,sum(trader_money) as m,sum(fund_count) as count from FUND_TRANSACTION b where unix_timestamp('2018-12-29 21:30:00')-unix_timestamp(`transaction_date`)<=86400 and `trader_money`>0 and status=0 and type='SELL' group by user_id,fund_id) c on a.user_id = c.user_id and a.`type`=CONCAT('FUND_',c.fund_id) set a.count=a.count-c.count;
        
        update USER_WALLET a inner join(select user_id,fund_id,sum(trader_money) as m,sum(money) as money from FUND_TRANSACTION b where unix_timestamp('2018-12-29 21:30:00')-unix_timestamp(`transaction_date`)<=86400 and `trader_money`>0 and status=0 and type='SELL' group by user_id,fund_id) c on a.user_id = c.user_id and a.`type`='USDT' set a.count=a.count+c.money ,a.money=a.money+c.money;
        
        
        
        UPDATE `FUND_TRANSACTION` SET status=1 WHERE unix_timestamp(transaction_date)-(unix_timestamp(now()))<86400 and status=0 ;

    END IF ;   
    
END$$
DELIMITER ;

SET @today = unix_timestamp('2018-12-29 21:30:00');
set @Fund1PriceDayliy=1;
set @Fund2PriceDayliy=1;
set @Fund3PriceDayliy=1;

call daliy_transaction(@Fund1PriceDayliy,@Fund2PriceDayliy,@Fund3PriceDayliy,@today);