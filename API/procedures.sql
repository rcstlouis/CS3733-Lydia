CREATE TRIGGER RenumberOnDeleteTrigger
BEFORE DELETE ON playlistEntries
REFERENCING
    NEW ROW AS newRow
    OLD ROW AS oldRow
FOR EACH ROW
    WHEN newRow.number > -- number of thing deleted
    UPDATE
;