---
title: Reactk开发 | React Router 基础
date:  2021年11月4日 16:24:27
comments: true
categories: "React开发"
tags: 
    - React
---

欢迎访问：[【React Native】从React开始——React Router 基础](https://blog.csdn.net/qq_26460841/article/details/121099969)



# 1. 前言
在上篇博客[【React Native】从React开始——核心概念](https://blog.csdn.net/qq_26460841/article/details/121046948)中了解了`React`的一些基础用法，并了解到其实和之前学习的微信小程序开发十分相像。感兴趣微信小程序开发的同学可以去同步了解学习下。在这篇博客中，将继续学习`React`路由基础部分的内容。当然这部分内容我在[官网](https://react.docschina.org/)中并没有找到，所幸在网上还是找到了一个开源的项目说明，即[getting started guide](https://github.com/remix-run/react-router/blob/main/docs/getting-started/installation.md)。接下来的内容将参考这篇文档的内容进行继续学习。

# 2. React路由基础
路由官方提供了一个强大的组件，即：[React Router](https://github.com/remix-run/react-router)。首先看下官网的介绍：
>React Router is a lightweight, fully-featured routing library for the React JavaScript library. React Router runs everywhere that React runs; on the web, on the server (using node.js), and on React Native.

翻译过来也即是：

>`React Router`是一个轻量级、功能齐全的路由库。`React`路由器运行在`React`运行的**任何地方**；在`web`、服务器（使用`node.js`）和`React Native`上。

所以学习这个库还是很有必要的。同时，在`Readme`文件中可以看到官方提供了学习的指引，即：[getting started guide](https://github.com/remix-run/react-router/blob/main/docs/getting-started/installation.md)。在这个文档中看到了这么一句话，更加证明了其重要的地位：
> React Router isn't just about matching a url to a function or component: it's about building a full user interface that maps to the URL.

首先按照这个文档来一起看下使用流程。

## 2.1 安装React Router
首先进入之前创建的项目目录，这里我的为`my-app`，然后执行命令：

```clike
npm install history react-router-dom
```
或者使用命令：

```clike
npm install react-router-dom
```

这里我去掉了版本。结果如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/7f2466cd218445d492ada2776e135867.png#pic_center =600x)
当然，在文件夹下可以做一个简单的检查：

![在这里插入图片描述](https://img-blog.csdnimg.cn/d0062cafcd7c4569988bb0a583f5abb3.png#pic_center =600x)

## 2.2 Create React App
在`Readme`文件中给出了几个使用案例，这里简单的来尝试下。首先导入路由模块：

```clike
// 导入路由的模块
import { BrowserRouter as Router, Route, Link } from "react-router-dom"
```

然后定义两个模块，分别是`LoginForm`和`HomePage`，其中`LoginForm`直接从上个博客[【React Native】从React开始——核心概念#表单](https://blog.csdn.net/qq_26460841/article/details/121046948#t12)中拷贝过来，这里仅简单的给出`HomePage`的模块代码：

```clike
const HomePage = () => <h1>主页</h1>
```
然后开始使用：

```clike
ReactDOM.render(
  <Router>
    <div className="textAlignCenter">
      <Route path="/login" component={LoginForm}></Route>
      <Route exact path="/" component={HomePage}></Route>
      <Link to="/">Home Page</Link><br/>
      <Link to="/login">Go to Login Page.</Link>
    </div>
  </Router>
  ,
  document.getElementById("root")
);
```

即，将要做路由的部分使用`Router`标签包裹起来，然后使用`Link` 来定义链接，对应于`HTML`中的`a`标签，`to`后面的东西对应`a`标签的`href`属性。`Route`标签内容东西为映射关系，即链接和对应的模块匹配设置。注意到：

```clike
<Route exact path="/" component={HomePage}></Route>
```
这里使用了`exact`这个属性，它的意思是精确的，表示精确匹配。因为对于网站默认的匹配为`/`，而对应的任意一个链接，比如`/a`、`/a/b/c`等均可以匹配到。比如如果去掉这个属性，当我们访问`/login`的时候，页面两个模块都会显示出来：

![在这里插入图片描述](https://img-blog.csdnimg.cn/dbd5cfe31ca943eebd19d9e20b3e5a7d.png#pic_center =800x)

如果加上这个`exact`属性，效果为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/565f4202d1f1431eae49feca7219c9c2.png#pic_center =800x)

## 2.3 React Router
通过官网找到了一个专门介绍`React Router`的网站：[https://reactrouter.com/](https://reactrouter.com/)。接下来就来一起看下这个网站的文档内容。当然在这之前，我将上面的案例进行简单的修改，使得更像一个网站。为了方便，首先设置一下在`VSCode`中的`html`标签代码补全。然后重新新建一个项目：

### 2.3.1 重新建立一个使用了Router的项目


```clike
npx create-react-app myapp
cd myapp
npm install react-router-dom  // 路由模块
yarn start
```

然后在`src`目录下的文件为：

![src](https://img-blog.csdnimg.cn/a8d22e8275b4483b973c0f5a852cdba7.png#pic_center =400x)
在接下来的案例中，就不将所有的内容都写在`index.js`文件中。我这里将它按照模块进行拆分。比如`Nav.js`文件中为：

```clike
import logo from './logo.svg';
import './App.css';

import { Link } from 'react-router-dom';

function Nav() {
  return (
    <div className="Nav">
        <div className="flex1">
            <img src={logo} className="App-logo" alt="logo" />
        </div>
        <ul className="flex1">
            <li><Link to="/">Home</Link></li>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/about">About</Link></li>
        </ul>
    </div>
  );
}

export default Nav;
```

其余的`About.js`以及`Home.js`采用类似的写法。然后在`index.js`文件中，做一下路由关联：

```clike
import './App.css'
import reactDom from 'react-dom'
import Nav from './Nav'
import Home from './Home'
import Login from './Login'
import About from './About'

import { BrowserRouter as Router, Route } from 'react-router-dom'

function App() {
  return (
    <div>
      <Router>
        <Nav />
        <Route path="/" exact component={Home}></Route>
        <Route path="/login" component={Login}></Route>
        <Route path="/about" component={About}></Route>
      </Router>
    </div>
  )
}

reactDom.render(
    <App />, 
    document.getElementById('root')
)
```
最后的效果为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/cf077110b1064d4fad47969c6a4c9c84.gif#pic_center =600x)
因为代码比较简单，这篇博客里面就不贴出来了。我上传到了`github`，代码链接为：[ReactLearn/src_11.04 v0/](https://github.com/baiyazi/ReactLearn/tree/main/src_11.04%20v0)

### 2.3.2 useParams
在上一小节中简单的使用了`React`路由，但在实际中，通常我们需要在链接后面拼接一些参数，比如：`https://editor.csdn.net/md?articleId=121095969`。在这个小节中，将介绍使用`URL`参数的使用。比如我们定义一个获取消息的模块：

```clike
// Message.js
import './App.css'
import { useParams } from 'react-router-dom'

function Message() {
  let { id } = useParams()
  console.log(id)
  return (
    <div className="HomeModule">
      <h1>The message is { id }</h1>
    </div>
  )
}

export default Message;
```

使用了`react-router-dom`中的`useParams`钩子函数。然后将其添加到我们`index.js`文件的路由模块下：

```clike
<Router>
    <Nav />
    <Switch>
        <Route path="/" exact component={Home}></Route>
        <Route path="/login" component={Login}></Route>
        <Route path="/about" component={About}></Route>
        <Route path="/:id" children={ <Message /> }></Route>
    </Switch>
</Router>
```
对了，这里使用了`Switch`标签，也来自`react-router-dom`。`<Switch>` 查看其所有子 `<Route>` 元素并呈现路径与当前 `URL` 匹配的第一个元素。 当您有多个路由，但您希望一次只渲染其中一个时使用 `<Switch>`就可以达到预期。

那么我们在测试的时候，截图如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/0f2a8e5582444cb1834f353abf962174.png#pic_center =600x)
![在这里插入图片描述](https://img-blog.csdnimg.cn/7a7f1fa4bcb6466b90530cf015668692.png#pic_center =600x)

结果可以发现通过这种方式，可以比较方便的获取到浏览器地址栏的参数。我上传到了`github`，代码链接为：[ReactLearn/src_11.04 v0.1/](https://github.com/baiyazi/ReactLearn/tree/main/src_11.04%20v0.1)

### 2.3.3 useRouteMatch 
`useRouteMatch` 将尝试将当前 `URL` 与给定的 `URL` 匹配，该 `URL` 可以是字符串或具有不同选项的对象。不妨先看看它会返回什么参数，改写下之前的`Message.js`案例：

```clike
import './App.css'
import { useRouteMatch, useParams } from 'react-router-dom'

function Message() {
    
  let { path, url } = useRouteMatch()
  let { id } = useParams()
  return (
    <div className="HomeModule">
      <h1>The message is {id}</h1>
      <h1>The message is {path}</h1>
      <h1>The message is {url}</h1>
    </div>
  )
}

export default Message
```
结果为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/47518e0f2a32480a8bb60c7d97e9e07e.png#pic_center =300x)
然后我们可以再次按照[网站文档](https://reactrouter.com/web/example/nesting)进行模仿改造。将`Message.js`改造为：

```clike
import './App.css'
import { useRouteMatch, useParams, Link, Switch, Route } from 'react-router-dom'

function Message() {
  let { path, url } = useRouteMatch()
  return (
    <div className="MessageModule">
      <div className="leftNav">
        <ul>
          <li>
            <Link to={`${url}/home`}>生活消息</Link>
          </li>
          <li>
            <Link to={`${url}/work`}>工作消息</Link>
          </li>
        </ul>
      </div>
      <div className="rightContent">
        <Switch>
          <Route path={path} exact></Route>
          <Route path={`${path}/:messageId`}>
            <Content />
          </Route>
        </Switch>
      </div>
    </div>
  )
}

const Content = () => {
  let { messageId } = useParams()
  return <p>Message is {messageId}</p>
}

export default Message
```
效果如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/c0e138abe8c94edcb42cad7282e7226d.png#pic_center =600x)

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ba7da8eef4c4359b3626e1e5611c9c6.png#pic_center =600x)
观察上面可以知道，可以很容易实现一个导航和页面的动态加载的需求。也即是说，对于动态定制页面来说十分方便。

我上传到了`github`，代码链接为：[ReactLearn/src_11.04 v0.2/](https://github.com/baiyazi/ReactLearn/tree/main/src_11.04%20v0.2)

### 2.3.4 useHistory
`useHistory`可以用来做导航**跳转**。比如在上面的案例中，进行修改`Message.js`：

```clike
import './App.css'
import {
  useRouteMatch,
  useParams,
  Link,
  Switch,
  Route,
  useHistory,
} from 'react-router-dom'

function Message() {
  let { path, url } = useRouteMatch()
  let history = useHistory()

  // 使用history来进行页面跳转
  function handleClick() {
    history.push('/login')
  }

  return (
    <div className="MessageModule">
      <div className="leftNav">
        <ul>
          <li>
            <Link to={`${url}/home`}>生活消息</Link>
          </li>
          <li>
            <Link to={`${url}/work`}>工作消息</Link>
          </li>
          <!--新增li项，并设置onClick回调-->
          <li onClick={handleClick}>To Login Page!</li>
        </ul>
      </div>
      <div className="rightContent">
        <Switch>
          <Route path={path} exact></Route>
          <Route path={`${path}/:messageId`}>
            <Content />
          </Route>
        </Switch>
      </div>
    </div>
  )
}

const Content = () => {
  let { messageId } = useParams()
  return <p>Message is {messageId}</p>
}

export default Message
```
效果为点击新增的`li`项后，会跳转到设置的`login`页面。由于比较简单，且未修改其余文件内容，这里就不上传代码了。

### 2.3.5 useLocation
返回一个表示当前`URL`的`location`对象。比如新写一个`Test`模块，然后在这个模块中`console.log`一下得到的`location`对象。获取到的内容为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/4e422d6ecae34dc99793e6254d3e5a49.png#pic_center =300x)
感觉和`window.location`对象的内容对应的，比如我们在浏览器控制台进行输出这个对象：

![在这里插入图片描述](https://img-blog.csdnimg.cn/c4ebd26d750b487bb09fda6b34c02f71.png#pic_center =400x)

### 2.3.6 Redirect
即重定向，和前面使用的`history.push("/login")`有些类似，但是`Redirect`将覆盖历史堆栈中的当前位置，就像服务器端重定向一样。下面看一个基础案例：

```clike
// Totest.js
import './App.css'
import { useLocation, Route, Redirect } from 'react-router-dom'

function Test() {
  let location = useLocation()
  let loggedIn = location.search.substring(1).split("=")[1] === "12345678"
  console.log(loggedIn);
  return (
      <Route>
        {loggedIn ? <Redirect to="/home" /> : <TouristPage />}
      </Route>
  )
}

const TouristPage = () => {
    return (
        <div>You do not have permission to access this site.</div>
    )
}

export default Test
```
效果为，当输入浏览器的链接为`http://localhost:3000/test?id=12345678`，就可以访问主页。除此之外的会显示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/a008f824d198453388c431910c66f3a8.png#pic_center =600x)

上面`<Redirect to="/home" />`中传入的是一个字符串，同样可以传入一个对象。用来指定一些参数，比如：

```clike
<Redirect
  to={{
    pathname: "/login",
    search: "?utm=your+face",
    state: { referrer: currentLocation }
  }}
/>
```
### 2.3.7 Query Parameters
在前面的案例中，我写了一行`bug`代码：`let loggedIn = location.search.substring(1).split("=")[1] === "12345678"`。这个代码无疑只是为了这里的案例用的。实际上，在React中提供了另一种更加优雅的方式来解决链接中的参数查询。可以查看官网案例：[here](https://v5.reactrouter.com/web/example/query-parameters)。这里，我来对上面的案例进行简单模仿改造一下。

```clike
import './App.css'
import { useLocation, Route, Redirect } from 'react-router-dom'

function Test() {
  // 用URLSearchParams来进行包装一次
  function useQuery() {
    return new URLSearchParams(useLocation().search)
  }

  let query = useQuery()
  console.log(query)

  // 直接使用get方法传入需要查询的key
  let loggedIn = query.get('id') === '12345678'

  return <Route>{loggedIn ? <Redirect to="/home" /> : <TouristPage />}</Route>
}

const TouristPage = () => {
  return <div>You do not have permission to access this site.</div>
}

export default Test
```
效果和上个案例一样，只是这样的写法的代码更加优雅。先使用`URLSearchParams`来进行将`window.location.search`的所有参数进行包装一下，然后直接使用`get`来进行按`key`进行获取。这种方式更加简单，所以也推荐使用这种方式。

### 2.3.8 No Match 404
首先为未匹配的链接页面配置好`404`页面，这里设置为`NoMatch.js`模块。具体内容如下：

```clike
import './App.css'
import { useLocation } from 'react-router-dom'

function NoMatch() {
  let location = useLocation()

  return (
    <div>
      <h3>
        No match for <code>{location.pathname}</code>
      </h3>
    </div>
  )
}

export default NoMatch
```
然后，需要在`index.js`文件中，配置默认的路由，即：

```clike
<Switch>
    <Route path="/" exact component={Home}></Route>
    <Route path="/login" component={Login}></Route>
    <Route path="/about" component={About}></Route>
    <Route path="/message" children={<Message />}></Route>
    <Route path="/test" component={Test}></Route>
    // 当匹配不到就走默认的NoMatch
    <Route path="*">
        <NoMatch />
    </Route>
</Switch>
```
比如随意输入一个链接地址，然后结果为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/45f8c58a8dd84e28857459c58a125c5d.png#pic_center =400x)


我上传到了`github`，代码链接为：[ReactLearn/src_11.04 v0.3/](https://github.com/baiyazi/ReactLearn/tree/main/src_11.04%20v0.3)

当然，在[这个网站](https://v5.reactrouter.com/web/guides/quick-start)上还有很多关于`React Router`的案例说明。感兴趣的可以继续看看这个网站。



___
**References**
- [React Router](https://github.com/remix-run/react-router)
- [【React Native】从React开始——核心概念](https://blog.csdn.net/qq_26460841/article/details/121046948)
- [getting started guide](https://github.com/remix-run/react-router/blob/main/docs/getting-started/installation.md)
- [2021 年你应该使用的 9 个 React Hook 的库](http://www.react-china.org/t/topic/37496)
- [https://reactrouter.com/](https://reactrouter.com/)
- [v5.reactrouter.com](https://v5.reactrouter.com/web/guides/quick-start)







