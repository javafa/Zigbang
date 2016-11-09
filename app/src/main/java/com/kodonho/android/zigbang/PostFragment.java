package com.kodonho.android.zigbang;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

import static com.jakewharton.rxbinding.widget.RxTextView.textChangeEvents;

public class PostFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        // 필드의 유효성 검증
        Button btnPost = (Button) view.findViewById(R.id.fab);
        btnPost.setEnabled(false);

        // 방제목
        // 보증금 월세
        // 사진 1개 필수
        ImageView phot1 = (ImageView) view.findViewById(R.id.roomPhoto1);
        // 위치 선택
        // 방개수
        // 평수

        Observable<TextViewTextChangeEvent> title = textChangeEvents((EditText)view.findViewById(R.id.etTitle));
        Observable<TextViewTextChangeEvent> deposite = textChangeEvents((EditText)view.findViewById(R.id.etDeposite));
        Observable<TextViewTextChangeEvent> count = textChangeEvents((EditText)view.findViewById(R.id.roomCount));



        Observable.combineLatest(title,deposite,count,
            (titleChange,depositeChange,countChange) -> {
                boolean titleCheck = titleChange.text().length() >= 1;
                boolean depositeCheck = depositeChange.text().length() >= 1;
                boolean countCheck = countChange.text().length() >= 1;
                return titleCheck && depositeCheck && countCheck;
            })
            .subscribe(
                checkFlag -> btnPost.setEnabled(checkFlag)
            );

        // Rx View Binding
        RxView.clicks(view.findViewById(R.id.fab))
            .subscribe(
                event -> {
                    // 여기서 값들을 firebase에 등록해준다
                }
            );


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
