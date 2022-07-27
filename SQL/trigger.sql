DELIMITER //

CREATE TRIGGER telegram_notification
    AFTER INSERT
    ON article
    FOR EACH ROW

BEGIN

    SET @DYNAMIC_COUNT = (select COUNT(value_text)
                          from dynamic_field_value
                          where object_id = (select ticket_id from article where article.id = NEW.id));

    INSERT INTO telegram_receive_notification_new_article
    (id_telegram, queue_id, tn, is_visible_for_customer, create_by, login_count_registration)
    VALUES ((
                CASE @DYNAMIC_COUNT
                    WHEN 0 THEN NULL
                    WHEN 1 THEN (select value_text
                                 from dynamic_field_value
                                 where object_id = (select ticket_id from article where article.id = NEW.id))
                    ELSE NULL
                    END),
            (select queue_id from ticket where id = (select ticket_id from article where id = NEW.id)),
            (select tn from ticket where id = (select ticket_id from article where id = NEW.id)),
            (select is_visible_for_customer from article where id = NEW.id),
            (select login from users where id = (select article.create_by from article where id = NEW.id)),
            (select COUNT(login) from telegram_bot_registration where login=(select login from users where id = (select article.create_by from article where id = NEW.id))));

END;
//

DELIMITER ;

# show triggers;

# select login from users where id=(select article.create_by from article where id = 709)

# select count(value_text)  from dynamic_field_value where object_id = (select ticket_id from article where article.id = 777);

# select count(ticket_id) from article where article.id = 777;

# drop trigger telegram_notification;
