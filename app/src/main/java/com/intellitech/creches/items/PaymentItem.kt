package com.intellitech.creches.items


import com.intellitech.creches.R
import com.intellitech.creches.models.Payment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.payment_item.view.*

class PaymentItem(private val payment: Payment): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.payment_date.text =""+payment.paimentDate
        viewHolder.itemView.payment_description.text=""+payment.description.capitalize()
        viewHolder.itemView.payment_amount.text = ""+payment.paimentAmmount
        if(payment.paid)
            viewHolder.itemView.payment_status.text = viewHolder.root.context.getString(R.string.payment_status_true)
        else{
            viewHolder.itemView.payment_status.text = viewHolder.root.context.getString(R.string.payment_status_false)
        }
    }

    override fun getLayout()= R.layout.payment_item

}