set echo off
set verify off
alter session set NLS_DATE_FORMAT='YYYY-MM-DD';
set serveroutput on
set feedback off

prompt <tasks>


prompt <task n="3.1">
prompt <![CDATA[
INSERT INTO events (event_code, customer_name, event_date,venue,time_of_day,security,music,sum,guest_count)
VALUES ('KM202', 'Kurely Mózes', to_date('2023-10-05','yyyy-mm-dd'), 'Budai Vár','n', 1, 1,  99000,  14);
prompt ]]>
prompt </task>

prompt <task n="3.2">
prompt <![CDATA[
UPDATE events
SET sum = 500000 , guest_count = 20
WHERE event_code = 'EM012';
prompt ]]>
prompt </task>

prompt <task n="3.3">
prompt <![CDATA[
UPDATE items
SET daily_cost = daily_cost * 0.95
WHERE purchase_date <= to_date('2009-09-23','yyyy-mm-dd');
prompt ]]>
prompt </task>

prompt <task n="3.4">
prompt <![CDATA[
UPDATE
(SELECT shipping_from
FROM orders
INNER JOIN items ON items.item_id = orders.item_id
WHERE shipping_from = 'foot' AND type = 'ent')
SET shipping_from = 'car';
prompt ]]>
prompt </task>

prompt <task n="3.5">
prompt <![CDATA[
DELETE FROM orders
WHERE order_comment IS NULL AND shipping_to = 'car' AND shipping_from = 'car';
prompt ]]>
prompt </task>

prompt <task n="3.6">
prompt <![CDATA[
INSERT INTO orders (item_id,event_id,liable_person,shipping_to,shipping_from,order_comment)
VALUES((SELECT item_id FROM items WHERE item_serial_code = 'LS00685190'), (SELECT event_id FROM events WHERE events.event_code = 'TN009'),'Kurely Mózes','car','car','Veszély várható!');
prompt ]]>
prompt </task>
prompt </tasks>