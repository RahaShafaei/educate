USE [educate]
GO
SET IDENTITY_INSERT [dbo].[org_unit] ON
    GO
-- ************************************************************************
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
    GO
    SET IDENTITY_INSERT [dbo].[org_unit] OFF
    GO
-- ************************************************************************
SET IDENTITY_INSERT [dbo].[element_grp] ON
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (1, N'attendance_status', N'حضورغیاب', 0, NULL, CAST(N'2023-10-29T09:41:47.547' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (2, N'plan_status', N'وضعیت برنامه ریزی', 0, NULL, CAST(N'2023-10-29T09:42:01.057' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (3, N'plan_type', N'نوع برنامه ریزی', 0, NULL, CAST(N'2023-10-29T09:42:17.253' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (4, N'edu_type', N'نوع آموزش', 0, NULL, CAST(N'2024-02-10T12:26:40.240' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (5, N'project_type', N'نوع پروژه', 0, NULL, CAST(N'2024-02-10T12:27:04.757' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (6, N'holding_type', N'نوع برگزاری دوره', 0, NULL, CAST(N'2024-02-12T08:38:10.013' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (7, N'org_type', N'نوع سازمان', 0, NULL, CAST(N'2024-02-12T10:09:11.013' AS DateTime))
    GO
INSERT [dbo].[element_grp] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (8, N'edu_phase', N'فاز آموزش', 0, NULL, CAST(N'2024-03-13T09:48:29.587' AS DateTime))
    GO
SET IDENTITY_INSERT [dbo].[element_grp] OFF
    GO
-- ************************************************************************
SET IDENTITY_INSERT [dbo].[element] ON
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (1, 1, N'Present', N'حاضر', 0, NULL, CAST(N'2023-10-29T09:43:38.693' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (2, 1, N'Absent', N'غایب', 0, NULL, CAST(N'2023-10-29T09:43:54.950' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (3, 2, N'Planning', N'برنامه ریزی', 0, NULL, CAST(N'2023-10-29T09:44:43.053' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (4, 2, N'Canceled', N'لغو', 0, NULL, CAST(N'2023-10-29T09:44:50.807' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (5, 2, N'Assessment', N'نیاز سنجی', 0, NULL, CAST(N'2023-10-29T09:45:01.177' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (6, 2, N'Done', N'انجام شده', 0, NULL, CAST(N'2023-10-29T09:45:08.780' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (7, 3, N'Managers', N'مدیران', 0, NULL, CAST(N'2023-10-29T09:45:56.143' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (8, 3, N'Technical', N'فنی', 0, NULL, CAST(N'2023-10-29T09:46:04.070' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (9, 3, N'General', N'عمومی', 0, NULL, CAST(N'2023-10-29T09:46:11.510' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (10, 4, N'Professional', N'دوره تخصصی', 0, NULL, CAST(N'2024-02-10T12:27:54.677' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (11, 4, N'General', N'دوره عمومی', 0, NULL, CAST(N'2024-02-10T12:28:15.820' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (12, 4, N'Manager', N'دوره مدیریتی', 0, NULL, CAST(N'2024-02-10T12:28:26.827' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (13, 5, N'IFANAP', N'آی فناپ', 0, NULL, CAST(N'2024-02-10T12:29:23.107' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (14, 5, N'MIDRP', N'میدآرپی', 0, NULL, CAST(N'2024-02-10T12:29:34.460' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (15, 6, N'Online', N'مجازی', 0, NULL, CAST(N'2024-02-12T08:39:22.393' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (16, 6, N'Inperson', N'حضوری', 0, NULL, CAST(N'2024-02-12T08:39:35.000' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (17, 7, N'BU', N'شرکت', 0, NULL, CAST(N'2024-02-12T10:09:36.040' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (18, 7, N'OU', N'مجتمع/ستاد', 0, NULL, CAST(N'2024-02-12T10:09:55.507' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (19, 7, N'root', N'root', 0, NULL, CAST(N'2024-03-12T08:59:58.183' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (20, 7, N'part', N'بخش', 0, NULL, CAST(N'2024-03-13T09:15:23.457' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (21, 4, N'test', N'آزمون', 0, NULL, CAST(N'2024-03-13T09:45:05.787' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (22, 4, N'other', N'سایر', 0, NULL, CAST(N'2024-03-13T09:45:28.763' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (23, 8, N'Establish', N'استقرار', 0, NULL, CAST(N'2024-03-13T09:50:00.167' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (24, 8, N'Version', N'نسخه جدید', 0, NULL, CAST(N'2024-03-13T09:50:21.683' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (25, 8, N'Retrian', N'بازآموزی', 0, NULL, CAST(N'2024-03-13T09:50:41.330' AS DateTime))
    GO
INSERT [dbo].[element] ([id], [element_grp_id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (26, 1, N'voluntary', N'داوطلبانه', 0, NULL, CAST(N'2024-03-13T10:27:48.307' AS DateTime))
    GO
SET IDENTITY_INSERT [dbo].[element] OFF
    GO
-- ************************************************************************
SET IDENTITY_INSERT [dbo].[roles] ON
    GO
INSERT [dbo].[roles] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (1, N'admin', N'مدیر', 0, NULL, CAST(N'2023-10-26T11:19:22.087' AS DateTime))
    GO
INSERT [dbo].[roles] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (2, N'user', N'کاربر', 0, NULL, CAST(N'2023-10-26T11:19:31.500' AS DateTime))
    GO
INSERT [dbo].[roles] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (3, N'teacher', N'مدرس', 0, NULL, CAST(N'2023-10-26T11:19:40.283' AS DateTime))
    GO
INSERT [dbo].[roles] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (4, N'student', N'دانشجو', 0, NULL, CAST(N'2023-10-26T11:19:44.570' AS DateTime))
    GO
INSERT [dbo].[roles] ([id], [lt_title], [pr_title], [deleted], [deleted_at], [inserted_at]) VALUES (9, N'supervisor', N'ناظر آموزش', 0, NULL, CAST(N'2024-03-13T09:19:03.153' AS DateTime))
    GO
SET IDENTITY_INSERT [dbo].[roles] OFF
    GO