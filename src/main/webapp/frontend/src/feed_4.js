import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

function feed()
{
    return (
        <div className="feed__outer-01" style={{overflow: 'scroll'}}>
            <Header1 />
            <Header2 />
            <div className="feed__inner-03">
                <div>
                    <span className="feed__question__text-01">분야</span>
                    <span className="feed__question__text-01"  style={{marginLeft:'300px'}}>난이도</span>
                </div>
                <input className="feed__question__input-01" type="text"></input>
                <select className="feed__question__input-01" style={{width:'217px', marginLeft:'-229px'}}>
                <option>★☆☆☆☆</option>
                <option>★★☆☆☆</option>
                <option>★★★☆☆</option>
                <option>★★★★☆</option>
                <option>★★★★★</option>
                </select>
                <div style={{marginTop:'15px'}}>
                    <span className="feed__question__text-01">제목</span>
                </div>
                <input className="feed__question__input-02" type="text"></input>
                <div style={{marginTop:'15px'}}>
                    <span className="feed__question__text-01">내용</span>
                </div>
                <div className="feed__question__input-03">
                <CKEditor
                    editor={ ClassicEditor }
                    onReady={ editor => {
                        // You can store the "editor" and use when it is needed.
                        console.log( 'Editor is ready to use!', editor );
                    } }
                    onChange={ ( event, editor ) => {
                        const data = editor.getData();
                        console.log( { event, editor, data } );
                    } }
                    onBlur={ ( event, editor ) => {
                        console.log( 'Blur.', editor );
                    } }
                    onFocus={ ( event, editor ) => {
                        console.log( 'Focus.', editor );
                    } }
                />
                </div>
                <div>
                <span className="feed__question__text-01" style={{marginTop: '38px', marginLeft: '580px'}}>정답</span>
                </div>
                <div>
                <input className="feed__question__input-01" style={{marginLeft:'590px'}} type="text"></input>
                </div>
                <div className="feed__question__button-01">글 작성</div>
                <div className="feed__question__button-02" style={{marginTop: '0px', marginLeft:'20px'}}>취소</div>
            </div>
            
        </div>
    );
}
export default feed;