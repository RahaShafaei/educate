INSERT INTO [educate].[dbo].[org_unit]
([parent_org_unit_id]
    ,[title]
    ,[code]
    ,[element_id_type]
    ,[descr]
    ,[deleted]
    ,[deleted_at]
    ,[inserted_at])
VALUES
    (NULL
        ,'root'
        ,'root'
        ,19
        ,'root'
        ,0
        ,NULL
        ,GETDATE())