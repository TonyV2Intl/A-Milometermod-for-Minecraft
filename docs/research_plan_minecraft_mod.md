# 研究计划: Minecraft 1.20.4 Fabric模组开发

## 目标
- 为开发一个名为“里程表模组”(Milometer Mod)的Minecraft 1.20.4 Fabric模组提供全面的技术指导。
- 详细说明开发环境配置、项目结构、核心功能实现（速度显示、距离统计、重置指令、UI配置）以及最佳实践。

## 研究分解
1.  **环境搭建与项目结构:**
    -   确定Fabric模组开发所需的工具（Minecraft版本, JDK, IDE, Fabric Loom）。
    -   研究Fabric模组的标准项目结构。
    -   分析 `fabric.mod.json` 配置文件的格式和关键属性。
    -   学习 `build.gradle` 文件以管理项目依赖和构建过程。

2.  **核心功能实现:**
    -   **玩家移动数据获取:**
        -   查找用于在客户端访问玩家位置和速度的Fabric API。
        -   研究客户端事件监听器，特别是与玩家Tick或移动相关的事件。
        -   确定如何根据玩家数据计算速度和累计距离。
    -   **HUD渲染:**
        -   研究Fabric的HUD渲染API（例如 `HudRenderCallback`）。
        -   学习如何在屏幕上绘制文本和UI元素。
        -   研究管理HUD元素位置的最佳实践。
    -   **指令系统:**
        -   学习用于注册客户端或服务端指令的Fabric API。
        -   找出实现一个用于重置累计距离的指令的方法。
    -   **配置文件管理:**
        -   研究用于创建和管理配置文件的库或方法（例如，用于保存HUD位置）。
        -   探索如Cloth Config等常用配置库或简单的JSON/properties文件方案。

3.  **代码示例与项目模板:**
    -   构建一个最小化的、可工作的项目结构。
    -   为每个核心功能编写示例代码片段。
    -   将这些代码片段整合到一个连贯且功能完整的示例模组中。

## 关键问题
1.  用于1.20.4模组开发的官方Fabric和Minecraft版本号是什么？
2.  如何监听客户端的玩家更新事件以在每个Tick获取位置数据？
3.  Fabric API中哪个类和事件提供了HUD渲染的能力？
4.  使用Fabric API注册客户端指令的现代化方法是什么？
5.  为模组创建简单的游戏内配置界面，推荐使用哪个库？
6.  `build.gradle` 文件应如何配置以正确引入Fabric API和其他依赖？
7.  `fabric.mod.json` 文件的确切结构是怎样的，包括客户端模组的入口点（entry points）定义？

## 资源策略
-   **主要数据源:**
    -   Fabric API官方文档网站。
    -   Fabric Wiki和相关教程。
    -   GitHub上已有的开源Fabric 1.20.4模组项目。
-   **搜索策略:**
    -   "Fabric modding tutorial 1.20.4"
    -   "Fabric API player movement event"
    -   "Fabric render HUD client side"
    -   "Fabric API register client command"
    -   "Fabric mod config library 1.20.4"
    -   "fabric.mod.json example"
    -   "fabric loom build.gradle"

## 验证计划
-   **源要求:** 优先使用Fabric官方文档和Fabric的GitHub组织。对于关键信息，将使用至少3个不同来源（官方文档、社区Wiki、示例模组仓库）进行交叉验证。
-   **交叉验证:** 比较来自不同来源的代码示例和配置细节，以确保它们是最新且遵循Fabric 1.20.4的最佳实践。

## 预期交付物
1.  项目根目录下的 `README.md` 文件，说明模组结构和构建方法。
2.  一个完整的、可构建的Fabric模组项目文件夹 (`milometermod`)，包含：
    -   `build.gradle` 和 `gradlew` 配置。
    -   `src/main/resources/fabric.mod.json`
    -   `src/main/java/com/tonyv2/milometermod/` 路径下的Java源代码，包括：
        -   主模组初始化器 (`MilometerMod.java`)。
        -   HUD渲染逻辑 (`MilometerHud.java`)。
        -   玩家移动的事件处理。
        -   指令注册。
        -   配置类。
3.  一份最终的技术研究报告 (`docs/research_report.md`)，详细说明研究发现，包括所用API的解释、代码片段和设计决策。

## 工作流选择
-   **主要焦点:** 搜索型工作流。
-   **理由:** 这是一个建设性任务，需要基于现有知识构建新的东西。搜索型方法最适合在整合最终产品之前，收集所有必要的构建块（API用法、配置、项目结构）。
