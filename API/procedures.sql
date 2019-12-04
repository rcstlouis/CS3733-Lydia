delimiter //

CREATE TRIGGER RenumberOnDeleteTrigger
AFTER DELETE ON playlistEntries
FOR EACH STATEMENT
BEGIN
    SET @pointer = SELECT COUNT(*) FROM playlistEntries;
    SET @pointer = @pointer + 1;
    REPEAT
        UPDATE playlistEntries SET NEW.playlistEntryNumber = @pointer - 1 WHERE OLD.playlistEntryNumber = @pointer;
    UNTIL
        SELECT COUNT(*) FROM playlistEntries WHERE 

    END REPEAT;
END;//
delimiter;