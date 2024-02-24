USE [master]
GO
/****** Object:  Database [educate]    Script Date: 2/24/2024 6:50:40 PM ******/
CREATE DATABASE [educate]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'educate', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\educate.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'educate_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\educate_log.ldf' , SIZE = 139264KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [educate] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [educate].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [educate] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [educate] SET ANSI_NULLS OFF
GO
ALTER DATABASE [educate] SET ANSI_PADDING OFF
GO
ALTER DATABASE [educate] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [educate] SET ARITHABORT OFF
GO
ALTER DATABASE [educate] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [educate] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [educate] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [educate] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [educate] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [educate] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [educate] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [educate] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [educate] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [educate] SET  DISABLE_BROKER
GO
ALTER DATABASE [educate] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [educate] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [educate] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [educate] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [educate] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [educate] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [educate] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [educate] SET RECOVERY FULL
GO
ALTER DATABASE [educate] SET  MULTI_USER
GO
ALTER DATABASE [educate] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [educate] SET DB_CHAINING OFF
GO
ALTER DATABASE [educate] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [educate] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [educate] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [educate] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'educate', N'ON'
GO
ALTER DATABASE [educate] SET QUERY_STORE = ON
GO
ALTER DATABASE [educate] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [educate]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_date_time_rand]    Script Date: 2/24/2024 6:50:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[fn_date_time_rand] ( @fromDate DATETIME2(0), @toDate DATETIME2(0)) returns DATETIME2(0)
as
begin

	DECLARE @Seconds INT = DATEDIFF(SECOND, @FromDate, @ToDate)
	DECLARE @Random INT = ROUND(((@Seconds-1) * (SELECT Value FROM vw_getRANDValue)), 0)

 return DATEADD(SECOND, @Random, @FromDate)
end
GO
/****** Object:  View [dbo].[vw_getRANDValue]    Script Date: 2/24/2024 6:50:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[vw_getRANDValue]
AS
SELECT RAND() AS Value
GO
/****** Object:  Table [dbo].[attendance]    Script Date: 2/24/2024 6:50:40 PM ******/
SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[attendance](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [person_id] [int] NULL,
    [plan_id] [int] NULL,
    [element_id] [int] NULL,
    [grade] [float] NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_attendance] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[element]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[element](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [element_grp_id] [int] NULL,
    [lt_title] [nvarchar](50) NULL,
    [pr_title] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_element] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[element_grp]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[element_grp](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [lt_title] [nvarchar](50) NULL,
    [pr_title] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_Type_grp] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[meeting]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[meeting](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [plan_id] [int] NULL,
    [file_value] [varbinary](max) NULL,
    [file_type] [nvarchar](max) NULL,
    [title] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_meeting] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[org_post]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[org_post](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [title] [nvarchar](255) NULL,
    [code] [nvarchar](50) NULL,
    [descr] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_org_post] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[org_unit]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[org_unit](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [parent_org_unit_id] [int] NULL,
    [title] [nvarchar](255) NULL,
    [code] [nvarchar](50) NULL,
    [element_id_type] [int] NULL,
    [descr] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_org_unit] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[org_unit_post_person]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[org_unit_post_person](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [org_unit_id] [int] NOT NULL,
    [org_post_id] [int] NULL,
    [person_id] [int] NOT NULL,
    [lt_from_date] [datetime] NULL,
    [pr_from_date] [nvarchar](30) NULL,
    [lt_to_date] [datetime] NULL,
    [pr_to_date] [nvarchar](30) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_org_unit_post_person] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[person]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[person](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [fname] [nvarchar](50) NULL,
    [lname] [nvarchar](50) NULL,
    [father_name] [nvarchar](50) NULL,
    [nl_code] [nvarchar](50) NULL,
    [pr_code] [nvarchar](50) NULL,
    [tel] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_person] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[person_role]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[person_role](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [person_id] [int] NOT NULL,
    [role_id] [int] NOT NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_person_role_1] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[plans]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[plans](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [org_unit_id] [int] NULL,
    [pr_course_id] [int] NULL,
    [person_id] [int] NULL,
    [element_id_type] [int] NULL,
    [element_id_status] [int] NULL,
    [element_id_edu] [int] NULL,
    [element_id_project] [int] NULL,
    [element_id_holding] [int] NULL,
    [title] [nvarchar](255) NULL,
    [lt_from_date] [datetime] NULL,
    [pr_from_date] [nvarchar](30) NULL,
    [lt_to_date] [datetime] NULL,
    [pr_to_date] [nvarchar](30) NULL,
    [plan_link] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_plan] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[pr_course]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[pr_course](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [pr_course_grp_id] [int] NULL,
    [lt_title] [nvarchar](50) NULL,
    [pr_title] [nvarchar](50) NULL,
    [descr] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_product_course] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[pr_course_grp]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[pr_course_grp](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [lt_title] [nvarchar](255) NULL,
    [pr_title] [nvarchar](255) NULL,
    [descr] [nvarchar](255) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_product_course_grp] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[roles]    Script Date: 2/24/2024 6:50:40 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[roles](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [lt_title] [nvarchar](50) NULL,
    [pr_title] [nvarchar](50) NULL,
    [deleted] [bit] NULL,
    [deleted_at] [datetime] NULL,
    [inserted_at] [datetime] NULL,
    CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[attendance] ADD  CONSTRAINT [DF_attendance_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[attendance] ADD  CONSTRAINT [DF_attendance_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[element] ADD  CONSTRAINT [DF_element_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[element] ADD  CONSTRAINT [DF_element_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[element_grp] ADD  CONSTRAINT [DF_element_grp_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[element_grp] ADD  CONSTRAINT [DF_Type_grp_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[meeting] ADD  CONSTRAINT [DF_meeting_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[meeting] ADD  CONSTRAINT [DF_meeting_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[org_post] ADD  CONSTRAINT [DF_org_post_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[org_post] ADD  CONSTRAINT [DF_org_post_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[org_unit] ADD  CONSTRAINT [DF_org_unit_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[org_unit] ADD  CONSTRAINT [DF_org_unit_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[org_unit_post_person] ADD  CONSTRAINT [DF_org_unit_post_person_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[org_unit_post_person] ADD  CONSTRAINT [DF_org_unit_post_person_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[person] ADD  CONSTRAINT [DF_person_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[person] ADD  CONSTRAINT [DF_person_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[person_role] ADD  CONSTRAINT [DF_person_role_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[person_role] ADD  CONSTRAINT [DF_person_role_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[plans] ADD  CONSTRAINT [DF_plans_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[plans] ADD  CONSTRAINT [DF_plan_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[pr_course] ADD  CONSTRAINT [DF_pr_course_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[pr_course] ADD  CONSTRAINT [DF_pr_course_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[pr_course_grp] ADD  CONSTRAINT [DF_pr_course_grp_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[pr_course_grp] ADD  CONSTRAINT [DF_pr_course_grp_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[roles] ADD  CONSTRAINT [DF_roles_deleted]  DEFAULT ((0)) FOR [deleted]
    GO
ALTER TABLE [dbo].[roles] ADD  CONSTRAINT [DF_role_inserted_at]  DEFAULT (sysdatetime()) FOR [inserted_at]
    GO
ALTER TABLE [dbo].[attendance]  WITH CHECK ADD  CONSTRAINT [FK_attendance_element] FOREIGN KEY([element_id])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[attendance] CHECK CONSTRAINT [FK_attendance_element]
    GO
ALTER TABLE [dbo].[attendance]  WITH CHECK ADD  CONSTRAINT [FK_attendance_person1] FOREIGN KEY([person_id])
    REFERENCES [dbo].[person] ([id])
    GO
ALTER TABLE [dbo].[attendance] CHECK CONSTRAINT [FK_attendance_person1]
    GO
ALTER TABLE [dbo].[attendance]  WITH CHECK ADD  CONSTRAINT [FK_attendance_plan] FOREIGN KEY([plan_id])
    REFERENCES [dbo].[plans] ([id])
    GO
ALTER TABLE [dbo].[attendance] CHECK CONSTRAINT [FK_attendance_plan]
    GO
ALTER TABLE [dbo].[element]  WITH CHECK ADD  CONSTRAINT [FK_element_element_grp] FOREIGN KEY([element_grp_id])
    REFERENCES [dbo].[element_grp] ([id])
    GO
ALTER TABLE [dbo].[element] CHECK CONSTRAINT [FK_element_element_grp]
    GO
ALTER TABLE [dbo].[meeting]  WITH CHECK ADD  CONSTRAINT [FK_meeting_plan] FOREIGN KEY([plan_id])
    REFERENCES [dbo].[plans] ([id])
    GO
ALTER TABLE [dbo].[meeting] CHECK CONSTRAINT [FK_meeting_plan]
    GO
ALTER TABLE [dbo].[org_unit]  WITH CHECK ADD  CONSTRAINT [FK_org_unit_element] FOREIGN KEY([element_id_type])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[org_unit] CHECK CONSTRAINT [FK_org_unit_element]
    GO
ALTER TABLE [dbo].[org_unit_post_person]  WITH CHECK ADD  CONSTRAINT [FK_org_unit_post_person_org_post] FOREIGN KEY([org_post_id])
    REFERENCES [dbo].[org_post] ([id])
    GO
ALTER TABLE [dbo].[org_unit_post_person] CHECK CONSTRAINT [FK_org_unit_post_person_org_post]
    GO
ALTER TABLE [dbo].[org_unit_post_person]  WITH CHECK ADD  CONSTRAINT [FK_org_unit_post_person_org_unit] FOREIGN KEY([org_unit_id])
    REFERENCES [dbo].[org_unit] ([id])
    GO
ALTER TABLE [dbo].[org_unit_post_person] CHECK CONSTRAINT [FK_org_unit_post_person_org_unit]
    GO
ALTER TABLE [dbo].[org_unit_post_person]  WITH CHECK ADD  CONSTRAINT [FK_org_unit_post_person_person] FOREIGN KEY([person_id])
    REFERENCES [dbo].[person] ([id])
    GO
ALTER TABLE [dbo].[org_unit_post_person] CHECK CONSTRAINT [FK_org_unit_post_person_person]
    GO
ALTER TABLE [dbo].[person_role]  WITH CHECK ADD  CONSTRAINT [FK_person_role_person] FOREIGN KEY([person_id])
    REFERENCES [dbo].[person] ([id])
    GO
ALTER TABLE [dbo].[person_role] CHECK CONSTRAINT [FK_person_role_person]
    GO
ALTER TABLE [dbo].[person_role]  WITH CHECK ADD  CONSTRAINT [FK_person_role_role] FOREIGN KEY([role_id])
    REFERENCES [dbo].[roles] ([id])
    GO
ALTER TABLE [dbo].[person_role] CHECK CONSTRAINT [FK_person_role_role]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plan_element_status] FOREIGN KEY([element_id_status])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plan_element_status]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plan_element_type] FOREIGN KEY([element_id_type])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plan_element_type]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plan_org_unit] FOREIGN KEY([org_unit_id])
    REFERENCES [dbo].[org_unit] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plan_org_unit]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plan_pr_course] FOREIGN KEY([pr_course_id])
    REFERENCES [dbo].[pr_course] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plan_pr_course]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plans_element_edu] FOREIGN KEY([element_id_edu])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plans_element_edu]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plans_element_holding] FOREIGN KEY([element_id_holding])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plans_element_holding]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plans_element_project] FOREIGN KEY([element_id_project])
    REFERENCES [dbo].[element] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plans_element_project]
    GO
ALTER TABLE [dbo].[plans]  WITH CHECK ADD  CONSTRAINT [FK_plans_person] FOREIGN KEY([person_id])
    REFERENCES [dbo].[person] ([id])
    GO
ALTER TABLE [dbo].[plans] CHECK CONSTRAINT [FK_plans_person]
    GO
ALTER TABLE [dbo].[pr_course]  WITH CHECK ADD  CONSTRAINT [FK_pr_course_pr_course_grp] FOREIGN KEY([pr_course_grp_id])
    REFERENCES [dbo].[pr_course_grp] ([id])
    GO
ALTER TABLE [dbo].[pr_course] CHECK CONSTRAINT [FK_pr_course_pr_course_grp]
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'attendance', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'element', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'element_grp', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'element_grp', @level2type=N'COLUMN',@level2name=N'deleted_at'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'meeting', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_post', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_unit', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org_unit_post_person', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'code melli' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person', @level2type=N'COLUMN',@level2name=N'nl_code'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'code personeli' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person', @level2type=N'COLUMN',@level2name=N'pr_code'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Person_id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person_role', @level2type=N'COLUMN',@level2name=N'id'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Person_id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person_role', @level2type=N'COLUMN',@level2name=N'person_id'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'person_role', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'plans', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Latin Name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'pr_course', @level2type=N'COLUMN',@level2name=N'lt_title'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Persian Name' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'pr_course', @level2type=N'COLUMN',@level2name=N'pr_title'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'pr_course', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'pr_course_grp', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0 for false, 1 for true' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'roles', @level2type=N'COLUMN',@level2name=N'deleted'
    GO
    USE [master]
    GO
ALTER DATABASE [educate] SET  READ_WRITE
GO
