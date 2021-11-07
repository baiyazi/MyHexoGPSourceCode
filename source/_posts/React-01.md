---
title: Reactk开发 | 核心概念
date: 2021年10月30日 22:31:40
comments: true
categories: "React开发"
tags: 
    - React
---

欢迎访问：[【React Native】从React开始——核心概念](https://blog.csdn.net/qq_26460841/article/details/121046948)


# 1. 前言
&ensp;&ensp;&ensp;&ensp;在上一篇[【React Native】回归跨平台开发——细细碎碎念念](https://blog.csdn.net/qq_26460841/article/details/121034480)中大致介绍了如何搭建环境的问题。在接下来的日子里将来学习下`React`这个框架。为什么呢？因为在`React Native`的官网的第一句话就是：
> **使用 `React` 来创建 `Android` 和 `iOS` 的原生应用**

不妨看看昨天的项目目录：

![在这里插入图片描述](https://img-blog.csdnimg.cn/7237e14c8e6b4319908b8c7f76199c1c.png#pic_center =700x)
可以发现这里有`android`和`ios`两个目录，这两个目录均是`RN`自动生成的，代码目录见下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/27f14fb344e84ecbbdc07cafadeff430.png#pic_center =600x)
可以发现其实就是原生开发的目录结构，打开`MainActivity.java`文件：

```clike
package com.awesomeproject;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "AwesomeProject";
  }
}
```
其实发现这里的写法就和原生的略有不同，猜测应该事`FaceBook`对`ReactActivity`进行了再次封装，以更方便的支持他提供的各种组件。

究竟事如何生成的这个原生代码部分，确实比较有意思。因为不光有`Android`的，还生成了`ios`的代码。当然更加有意思的是：

![在这里插入图片描述](https://img-blog.csdnimg.cn/bc03d28bb3384a2889043f628df52068.png#pic_center =500x)
也就是说其实可以做到混合开发，无缝集成。

所以还是有必要好好学习`RN`，因为之前没有学过`React`，所以接下来就好好学习一下这个框架以及复习下`JavaScript`。无意间看到了这句话，感觉比较搞笑，但也说明了`React`其重要的地位：

![在这里插入图片描述](https://img-blog.csdnimg.cn/602b8f829f0448cab1221a8b9e57ef8b.png#pic_center =500x)
# 2. React
首先找到其[官方文档](https://react.docschina.org/)，我们知道`React`它是一个用于构建用户界面的 [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) 库，由`Facebook`于`2013`年`5`月开源。`React`主要用于构建`UI`，和`Vue`有些类似。下面正式进入正题。

开始一个 `React` 应用，可选择两种方式：
- 通过 `HTML` 的 `script` 标签引入 `React` ，具体可参考：[在网站中添加 React](https://react.docschina.org/docs/add-react-to-a-website.html)；
- 搭建本地开发环境；

因为第一种方式比较简单，直接参考官方文档即可，这里记录下第二种方式。首先需要确保安装了`Node.js`，需要确保 `Node >= 14.0.0` 和 `npm >= 5.6`。这里还是检查下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/edd999a35cdd4c9ba4d8ba514611f579.png#pic_center)

这里我需要更新一下`node`的版本。[下载最新版](https://nodejs.org/en/)安装即可。

然后执行：

```clike
npx create-react-app my-app
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/50a2861d16d04f63a012cc32ecd9c1b9.png#pic_center =600x)

按照创建完毕后的提示：

```clike
yarn start # 启动服务
yarn build # 编译为静态文件
yarn test # 运行测试
```
那么这里直接运行下，即：

```clike
cd my-app
yarn start
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d6a340f16ea945ae80bf2be664c2c230.png#pic_center =500x)
按照提示浏览器访问：[http://localhost:3000/](http://localhost:3000/)，即可看见一个`React`的图标效果。

## 2.1 编辑器配置语法高亮
推荐参照[这篇教程](https://babeljs.io/docs/editors/)来给你的编辑器配置语法高亮。

因为`Webstorm`只提供`30`天白嫖，所以以后还是使用`Visual Studio`来进行代码编写。所以按照文档说明，这里安装下 `vscode-language-babel` 这个插件。即：

![在这里插入图片描述](https://img-blog.csdnimg.cn/86b9a5144f7547f3aa111eef9c76b792.png#pic_center =400x)

然后搜索 `vscode-language-babel` 这个插件安装即可。

![在这里插入图片描述](https://img-blog.csdnimg.cn/cd5420f950964b84b9cf28f416fdd0ea.png#pic_center =700x)
## 2.2 Hello World!
不妨看下这个项目的文件结构：

![在这里插入图片描述](https://img-blog.csdnimg.cn/8ab587c0c26c43efa5e0036dbbad00e1.png#pic_center =700x)

其实和之前的`hexo`有些类似，`public`为生成的`html`页面，`node_modules`为`nodejs`的一些依赖文件，而真正的写代码的地方在`src`目录中。

不妨删除src目录下的其余文件，仅留下`index.js`和`index.css`文件，然后将`index.js`文件内容修改为一个正经的`Hello World!`，如下：

```clike
import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'

ReactDOM.render(
  <h1>Hello, world!</h1>, 
  document.getElementById('root')
)
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/9376fdfb70ad4ba095770addd1cf4126.png#pic_center =400x)
## 2.3 取消VSCode的自动代码折叠
但是`VSCode`的`ctrl+s`后自动代码折叠可是够麻烦的，所以这里取消一下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f183b360f13d4d5680c7cc0b9f7d95bc.png#pic_center)
## 2.4 语法和使用
### 2.4.1 元素渲染
在之前的案例中代码如下：

```clike
ReactDOM.render(
  <h1>Hello, world!</h1>, 
  document.getElementById('root')
)
```

我们看到了[ReactDOM.render()](https://react.docschina.org/docs/react-dom.html#render)函数进行渲染，其中传入了两个参数，分别是待渲染元素和`DOM`根。在`React`中，更新 `UI` 唯一的方式是创建一个全新的元素，并将其传入 `ReactDOM.render()`进行渲染。所以在需要更新的地方都需要使用这个函数，比如下面的案例：

```clike
let number = 1;

setInterval(function(){
  number += 1;
  ReactDOM.render(
    <h1>Number is {number}</h1>, 
    document.getElementById('root')
  )
}, 1000);
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/856a8bfadbde4256839f788cb5189e02.png#pic_center)
并会每一秒钟进行自加`1`。当然也可以参考官方提供的这个例子：[一个计时器的例子](https://codepen.io/pen?&editors=0010)

### 2.4.2 组件
在开发微信小程序的时候，为了少写一些代码通常都需要封装一些组件，以提高代码的复用。在`React`中也提供 组件的封装，首先从函数组件开始。
#### 2.4.2.1 函数组件
即编写`JavaScript` 函数，有两种形式，分别是：

```clike
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```
和

```clike
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```
上述两个组件在 `React` 里是等效的。

#### 2.4.2.2 渲染组件
在前面的案例中，其实也写过这个部分，其实也就是使用 `ReactDOM.render()`进行渲染。比如：

```clike
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

const element = <Welcome name="Sara" />;
ReactDOM.render(
  element,
  document.getElementById('root')
);
```
效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/09f6e4f01a0747a0ac94fbf5814a8806.png)
因为首先定义了一个函数组件`<Welcome>`，而其属性都会传入`props`这个对象中，所以最终显示结果为上图所示。

**注意： 组件名称必须以大写字母开头。**
**`React` 会将以小写字母开头的组件视为原生 `DOM` 标签。**

### 2.4.3 生命周期
为了使用生命周期方法，我们需要使用函数组件的第二种形式，即创建一个同名的 `ES6 class`，并且继承于 `React.Component`，函数主体写在`render()`方法中。和Java等面向对象语言类似，也有构造方法`constructor`，可以用来接收参数`props`。

为了使在元素渲染部分的案例的计数器更加像一个正规的组件，我们假定最终的调用为：

```clike
ReactDOM.render(
  <CountNumber />,
  document.getElementById('root')
);
```

那么我们就需要对之前的代码进行组件化。在这里引入两个生命周期方法，分别是挂载（`mount`）和卸载（`unmount`）。分别在组件第一次被渲染到 `DOM` 中的时候和被删除的时候执行。

```clike
class CountNumber extends React.Component{
  constructor(props){
    super(props)
    this.state = {data: props.number}
  }

  componentDidMount(){
    this.timerId = setInterval(() => this.update(), 1000);
  }

  componentWillUnmount(){
    clearInterval(this.timerId);
  }

  update(){
    var _number = this.state.data;
    this.setState({
      data: _number + 1
    })
  }

  render(){
    return (
      <div>
        <h1>Number is {this.state.data}</h1>
      </div>
    )
  }
}


ReactDOM.render(
  <CountNumber number={1} />,
  document.getElementById('root')
);
```
效果和前面的一样，也就是自动累加更新。

注意到上面更新数据的时候使用了`this.setState(obj)`方法，有点类似于微信小程序，提供的用来更新数据的方法。因为`this.props`是只读的，不支持修改。`state` 是默认的私有属性，并且完全受控于当前组件。所以如果数据需要更新（即修改），就需要将数据从`props`中拷贝到`state`中，进而使用 `this.setState()` 来时刻更新组件 `state`。

**需要注意的是**：**构造函数是唯一可以给 `this.state` 赋值的地方**。且不能直接修改state，即下面的写法是错误的：

```clike
this.state.comment = 'Hello';
```
应该是：
```clike
this.setState({comment: 'Hello'});
```
这让我想起了之前刚开发微信小程序的时候，也有这个问题，当时就错误的直接赋值修改了，导致其修改并没有生效。

当然，在进行更新的时候，也可以传入一个函数，比如：

```clike
this.setState(function(state, props) {
  return {
    counter: state.counter + props.increment
  };
});
```
只要最终返回的值是一个键值对形式的即可。

### 2.4.3 事件处理
和`HTML`中类似，也支持`onClick`事件，只是写法上略有不同。同时在 `React` 中另一个不同点是你不能通过返回 `false` 的方式阻止默认行为。你必须显式的使用 `preventDefault` 。比如在下面的案例中，使用`a`标签，默认链接到百度，但是这里使用`preventDefault`来阻止默认行为：

```clike
class LinkButton extends React.Component{
  constructor(props){
    super(props)
    this.state = {data:props.text, _default:"https://www.baidu.com"}

    // 为了在回调中使用 `this`，这个绑定是必不可少的
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick(e){
    console.log(e);
    // 阻止默认行为。
    e.preventDefault();
  }

  render(){
    return (
      <a href={this.state._default} className="button" onClick={(e) => this.handleClick(e)}>{this.state.data}</a>
    )
  }
}


ReactDOM.render(
  <LinkButton text={"百度搜索"} />,
  document.getElementById('root')
);
```

至于类样式文件定义在`index.css`中，和`html`中使用的一样，这里就不再给出。实际测试发现确实可以阻止了默认的跳转行为。

### 2.4.4 列表

```clike
function NumberList(props) {
  const numbers = props.numbers;
  const listItems = numbers.map((number) =>
    <li key={number.toString()}>
      {number}
    </li>
  );
  return (
    <ul>{listItems}</ul>
  );
}

const numbers = [1, 2, 3, 4, 5];
ReactDOM.render(
  <NumberList numbers={numbers} />,
  document.getElementById('root')
);
```
其结果也就是：
![在这里插入图片描述](https://img-blog.csdnimg.cn/d471b24976bc42fbaa1803e0294af178.png)
其中， `map()` 函数是`JavaScript`中提供的用来进行列表处理的一个函数，比如下面我们让数组中的每一项变双倍，从而得到一个新的列表，代码为：

```clike
numbers.map((number) => number * 2);
```
结果为：`[2, 4, 6, 8, 10]`

需要注意的是，对于创建的列表元素，对于每一项应该有个`key`字段进行标识。

### 2.4.5 表单
对于表单控件，其使用还是和`HTML`中的保持一致。比如下面封装一个登录页面的控件：

![在这里插入图片描述](https://img-blog.csdnimg.cn/98ad4bb152604caf833a128a0cf7dab0.png#pic_center)

```clike
class LoginForm extends React.Component{
  constructor(props){
    super(props)
    this.state = {
          username: props.username === undefined ? "" : props.username,
          passwd: props.passwd === undefined ? "" : props.passwd
    }
    this.handleSubmit = this.handleSubmit.bind(this)
    this.handleChange = this.handleChange.bind(this)

  }

  handleSubmit(event){
    console.log(this.state.username +"\t" + this.state.passwd);
    // 阻止默认行为。
    event.preventDefault();
  }

  handleChange(event){
    var _value = event.target.value;
    var _name = event.target.name;
    if(_name === "passwd"){
      this.setState({
        passwd: _value
      });
    }else{
      this.setState({
        username: _value
      });
    }
  }

  render(){
    return (
      <form onSubmit={this.handleSubmit}>
        <div className="flexontainer">
          <div className="center">简易版用户登录</div>
        </div>
        <div className="flexontainer">
          <div>用户名：</div>
          <div>
            <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/>
          </div>
        </div>
        <div className="flexontainer">
          <div>密码：</div>
          <div>
            <input type="password" name="passwd" value={this.state.passwd} onChange={this.handleChange}/>
          </div>
        </div>
        <input type="submit" value="登录" className="submitButton"/>
      </form>
    )
  }
}
```
当然，对应的`css`的样式也很简单：

```css
form{
  width: 500px;
  display: block;
  margin: 20px auto;
  background-color: rgb(201, 198, 198);
  padding: 8px;
}

.flexontainer{
  display: flex;
  flex-direction: row;
  height: 2rem;
  height: 2rem;
  line-height: 2rem;
  text-align: center;
  margin-bottom: 8px;
}
.flexontainer div:first-child{
  text-align: right;
}
.flexontainer div:first-child{
  flex: 1;
}
.flexontainer div:last-child{
  flex: 2;
}
.flexontainer div{
  text-align: left;
}
.flexontainer div>input{
  height: 1.2rem;
  min-width: 200px;
}
.submitButton{
  display: block;
  min-width: 300px;
  height: 1.8rem;
  line-height: 1.8em;
  cursor: pointer;
  margin: 0 auto;
}
.flexontainer div.center{
  text-align: center;
}
```

使用比较简单，直接调用这个组件即可：

```clike
ReactDOM.render(
  <LoginForm username={'李四'} passwd={123}/>,
  document.getElementById("root")
);
```
这里为表单传入了默认值，所以我们需要在自定义的组件中进行值非空的判断。因为每次`input`输入框都会触发`handleChange`方法，所以这里可以自动更新。

至于其余的表单也和`HTML`中的一样，就不再继续。

### 2.4.6 状态提升
官网原话是这么说的：
> 通常，多个组件需要反映相同的变化数据，这时我们建议将共享状态提升到最近的共同父组件中去。

看到这句话我想到了`JavaScript`中的父子元素之间的冒泡事件。看了一遍官网的[状态提升](https://react.docschina.org/docs/lifting-state-up.html)说明，感觉主要思想在于如何将子控件的值及时反馈到父控件，以及父控件如何将值传递到子控件中。接着上个案例修改，比如做一个两个同步的提交表单：

![在这里插入图片描述](https://img-blog.csdnimg.cn/cc6215f9f90b4434886ec24802be0527.png#pic_center)
在`LoginForm`中不再使用`state`这个状态，而是直接使用`props`这个只读变量来设置一开始的值，然后直接回传用户输入的结果到父控件即可。

```clike
class LoginForm extends React.Component{
  constructor(props){
    super(props)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.handleChange = this.handleChange.bind(this)

  }

  handleSubmit(event){
    console.log(this.props.username +"\t" + this.props.passwd);
    // 阻止默认行为。
    event.preventDefault();
  }

  handleChange(event){
    var _value = event.target.value;
    var _name = event.target.name;
    if(_name === "passwd"){
      // 新增回调函数
      this.props.onPasswordChange(_value);
    }else{
       // 新增回调函数
       this.props.onUserNameChange(_value);
    }
  }

  render(){
    return (
      <form onSubmit={this.handleSubmit}>
        <div className="flexontainer">
          <div className="center">简易版用户登录</div>
        </div>
        <div className="flexontainer">
          <div>用户名：</div>
          <div>
            <input type="text" name="username" value={this.props.username} onChange={this.handleChange}/>
          </div>
        </div>
        <div className="flexontainer">
          <div>密码：</div>
          <div>
            <input type="password" name="passwd" value={this.props.passwd} onChange={this.handleChange}/>
          </div>
        </div>
        <input type="submit" value="登录" className="submitButton"/>
      </form>
    )
  }
}
```
定义父控件为`ParentContainer`：

```clike
class ParentContainer extends React.Component{
  constructor(props){
    super(props)
    this.state = {
        username : '张三',
        passwd : 123
    };
    this.handlePasswordChange = this.handlePasswordChange.bind(this)
    this.handleUserNameChange = this.handleUserNameChange.bind(this)
  }

  handleUserNameChange(value){
    console.log("handleUserNameChange " + value);
    this.setState({
      username : value
    });
    console.log("handleUserNameChange " + this.state.username);
  }

  handlePasswordChange(value){
    console.log("handlePasswordChange " + value);
    this.setState({
      passwd : value
    });
    console.log("handlePasswordChange " + this.state.passwd);
  }

  render(){
    return(
      <div>
        <LoginForm 
          username={this.state.username} 
          passwd={this.state.passwd} 
          onUserNameChange={this.handleUserNameChange} 
          onPasswordChange={this.handlePasswordChange} 
          />

        <LoginForm 
          username={this.state.username} 
          passwd={this.state.passwd} 
          onUserNameChange={this.handleUserNameChange} 
          onPasswordChange={this.handlePasswordChange} 
          />
      </div>
    )
  }
}
```
使用自定义回调接口，感觉其写法有点类似于`Java`语言中的接口的思想。需要注意的是这里为了实现状态提升，在子控件中就不再是直接设置，而是将这个权限交给了父控件，由父控件来完成。而如果是子控件设置的话，就有点类似于自己临时拷贝一块内存区域，所以不会影响到原本的结果。将导致两个部分就不会同步。最终的调用如下：

```clike
ReactDOM.render(
  <ParentContainer/>,
  document.getElementById("root")
);
```
### 2.4.7 组合
**在`React`中推荐使用组合而非继承来实现组件间的代码重用**。比如上面的那个案例就是一个组合的例子，当然还有一种组合方式，感觉比较巧妙，那就是可以组合在自定义控件的内部，比如下面的案例：

![在这里插入图片描述](https://img-blog.csdnimg.cn/e736a6a39c314e36b9cab9b0bd376396.png#pic_center =600x)


注意下面的写法，因为需要在自定义组件内部添加子元素，所以这里的写法为：`{props.children}`

```clike
function FancyBorder(props){
  return (
    <div className={'FancyBorder color-' + props.color}>
      {props.children}
    </div>
  );
}

function WelcomeDialog(){
  return (
    <FancyBorder color="red">
      <h1 className="Dialog-title">
        Welcome
      </h1>
      <p className="Dialog-message">
        Thank you for visiting our spacecraft!
      </p>
    </FancyBorder>
  );
}
```

对应的样式为：

```css
.FancyBorder {
  max-width: 500px;
  padding: 10px 10px;
  border: 10px solid;
  margin: 10px auto;
}

.color-red {
  border-color: red;
}

.Dialog-title {
  margin: 0;
  font-family: sans-serif;
}

.Dialog-message {
  font-size: larger;
}
```
调用：

```clike
ReactDOM.render(
  <WelcomeDialog />,
  document.getElementById('root')
);
```

### 2.4.8 React 哲学
[React 哲学](https://react.docschina.org/docs/thinking-in-react.html)

# 3. 后记
大致过了一遍`React`的基础语法，总体来和`H5`的开发，乃至之前的微信小程序开发还是很相似的。在接下来的日子将继续看React的[高级指引](https://react.docschina.org/docs/getting-started.html)部分。

___
**Thanks**
- [React 开始](https://react.docschina.org/docs/getting-started.html)









